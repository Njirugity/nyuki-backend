package com.nyuki.nyuki_backend.focussession.service.impl;

import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import com.nyuki.nyuki_backend.common.exceptions.FocusSessionNotFoundException;
import com.nyuki.nyuki_backend.common.exceptions.InvalidRequestException;
import com.nyuki.nyuki_backend.common.exceptions.ScheduleNotFoundException;
import com.nyuki.nyuki_backend.common.exceptions.UserNotFoundException;
import com.nyuki.nyuki_backend.focussession.dto.CreateFocusSessionDto;
import com.nyuki.nyuki_backend.focussession.dto.FocusSessionResponseDto;
import com.nyuki.nyuki_backend.focussession.dto.UpdateFocusSessionDto;
import com.nyuki.nyuki_backend.focussession.entity.FocusSession;
import com.nyuki.nyuki_backend.focussession.mapper.FocusSessionMapper;
import com.nyuki.nyuki_backend.focussession.repository.FocusSessionRepository;
import com.nyuki.nyuki_backend.focussession.service.FocusSessionService;
import com.nyuki.nyuki_backend.schedule.entity.Schedule;
import com.nyuki.nyuki_backend.schedule.repository.ScheduleRepository;
import com.nyuki.nyuki_backend.users.entity.Users;
import com.nyuki.nyuki_backend.users.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FocusSessionServiceImpl implements FocusSessionService {

    private final FocusSessionRepository focusSessionRepository;
    private final FocusSessionMapper focusSessionMapper;
    private final ScheduleRepository scheduleRepository;
    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public FocusSessionResponseDto create(CreateFocusSessionDto dto, String email){
        Users owner = usersRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User not found")
        );
        Schedule schedule = scheduleRepository.findByIdAndOwnerEmail(dto.scheduleId(), email).orElseThrow(()->
                new ScheduleNotFoundException("Schedule not found")
        );
        FocusSession focusSession = focusSessionMapper.toFocusSession(dto);
        if (focusSession.getStartedAt() == null) {
            focusSession.setStartedAt(LocalDateTime.now());
        }
        focusSession.setSchedule(schedule);
        focusSession.setTask(schedule.getTask());
        focusSession.setOwner(owner);
        focusSession.setPausedSeconds(0L);
        focusSession.setStatus(focusSession.getEndedAt() == null ? ProgressStatus.ACTIVE : ProgressStatus.COMPLETED);
        applyDuration(focusSession);
        focusSessionRepository.save(focusSession);
        return focusSessionMapper.toDto(focusSession);
    }

    @Override
    public FocusSessionResponseDto getFocusSession(UUID focusSessionId, String email){
        return focusSessionMapper.toDto(findOwnedFocusSession(focusSessionId, email));
    }

    @Override
    public Page<FocusSessionResponseDto> getFocusSessions(String email, UUID scheduleId, UUID taskId,
                                                          ProgressStatus status, Pageable pageable){
        return focusSessionRepository.searchByOwner(email, scheduleId, taskId, status, pageable)
                .map(focusSessionMapper::toDto);
    }

    @Override
    @Transactional
    public FocusSessionResponseDto update(UUID focusSessionId, UpdateFocusSessionDto dto, String email){
        FocusSession focusSession = findOwnedFocusSession(focusSessionId, email);
        boolean cancelling = dto.status() == ProgressStatus.CANCELLED;
        if (dto.status() != null && !cancelling) {
            throw new InvalidRequestException("Status can only be set to CANCELLED here; use pause, resume or stop");
        }
        if (dto.endedAt() != null && isRunning(focusSession) && !cancelling) {
            throw new InvalidRequestException("Use stop to end a running session");
        }
        focusSessionMapper.updateFocusSession(dto, focusSession);
        if (cancelling && focusSession.getEndedAt() == null) {
            focusSession.setEndedAt(LocalDateTime.now());
        }
        if (cancelling) {
            closePause(focusSession, focusSession.getEndedAt());
        }
        applyDuration(focusSession);
        focusSessionRepository.save(focusSession);
        return focusSessionMapper.toDto(focusSession);
    }

    @Override
    @Transactional
    public void delete(UUID focusSessionId, String email){
        focusSessionRepository.delete(findOwnedFocusSession(focusSessionId, email));
    }

    @Override
    @Transactional
    public FocusSessionResponseDto pause(UUID focusSessionId, String email){
        FocusSession focusSession = findOwnedFocusSession(focusSessionId, email);
        if (focusSession.getStatus() != ProgressStatus.ACTIVE) {
            throw new InvalidRequestException("Only an active session can be paused");
        }
        focusSession.setPausedAt(LocalDateTime.now());
        focusSession.setStatus(ProgressStatus.PAUSED);
        focusSessionRepository.save(focusSession);
        return focusSessionMapper.toDto(focusSession);
    }

    @Override
    @Transactional
    public FocusSessionResponseDto resume(UUID focusSessionId, String email){
        FocusSession focusSession = findOwnedFocusSession(focusSessionId, email);
        if (focusSession.getStatus() != ProgressStatus.PAUSED) {
            throw new InvalidRequestException("Only a paused session can be resumed");
        }
        closePause(focusSession, LocalDateTime.now());
        focusSession.setStatus(ProgressStatus.ACTIVE);
        focusSessionRepository.save(focusSession);
        return focusSessionMapper.toDto(focusSession);
    }

    @Override
    @Transactional
    public FocusSessionResponseDto stop(UUID focusSessionId, String email){
        FocusSession focusSession = findOwnedFocusSession(focusSessionId, email);
        if (!isRunning(focusSession)) {
            throw new InvalidRequestException("Only an active or paused session can be stopped");
        }
        LocalDateTime now = LocalDateTime.now();
        closePause(focusSession, now);
        focusSession.setEndedAt(now);
        focusSession.setStatus(ProgressStatus.COMPLETED);
        applyDuration(focusSession);
        focusSessionRepository.save(focusSession);
        return focusSessionMapper.toDto(focusSession);
    }

    private boolean isRunning(FocusSession focusSession){
        return focusSession.getStatus() == ProgressStatus.ACTIVE || focusSession.getStatus() == ProgressStatus.PAUSED;
    }

    private void closePause(FocusSession focusSession, LocalDateTime at){
        if (focusSession.getPausedAt() == null) {
            return;
        }
        long paused = Math.max(0, Duration.between(focusSession.getPausedAt(), at).toSeconds());
        focusSession.setPausedSeconds(pausedSeconds(focusSession) + paused);
        focusSession.setPausedAt(null);
    }

    private long pausedSeconds(FocusSession focusSession){
        return focusSession.getPausedSeconds() == null ? 0 : focusSession.getPausedSeconds();
    }

    private void applyDuration(FocusSession focusSession){
        LocalDateTime start = focusSession.getStartedAt();
        LocalDateTime end = focusSession.getEndedAt();
        if (end == null) {
            focusSession.setDuration(null);
            return;
        }
        if (!end.isAfter(start)) {
            throw new InvalidRequestException("End time must be after start time");
        }
        long duration = Duration.between(start, end).toSeconds() - pausedSeconds(focusSession);
        if (duration < 0) {
            throw new InvalidRequestException("Paused time cannot exceed the session length");
        }
        focusSession.setDuration(duration);
    }

    private FocusSession findOwnedFocusSession(UUID focusSessionId, String email){
        return focusSessionRepository.findByIdAndOwnerEmail(focusSessionId, email).orElseThrow(()->
                new FocusSessionNotFoundException("Focus session not found")
        );
    }
}
