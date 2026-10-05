package com.nyuki.nyuki_backend.focussession.mapper;

import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import com.nyuki.nyuki_backend.focussession.dto.CreateFocusSessionDto;
import com.nyuki.nyuki_backend.focussession.dto.FocusSessionResponseDto;
import com.nyuki.nyuki_backend.focussession.dto.UpdateFocusSessionDto;
import com.nyuki.nyuki_backend.focussession.entity.FocusSession;
import com.nyuki.nyuki_backend.schedule.entity.Schedule;
import com.nyuki.nyuki_backend.tasks.entity.Task;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-05T17:34:56+0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.100.v20260826-1225, environment: Java 21.0.12.1 (Eclipse Adoptium)"
)
@Component
public class FocusSessionMapperImpl implements FocusSessionMapper {

    @Override
    public FocusSession toFocusSession(CreateFocusSessionDto dto) {
        if ( dto == null ) {
            return null;
        }

        FocusSession focusSession = new FocusSession();

        focusSession.setEndedAt( dto.endedAt() );
        focusSession.setStartedAt( dto.startedAt() );

        return focusSession;
    }

    @Override
    public FocusSessionResponseDto toDto(FocusSession focusSession) {
        if ( focusSession == null ) {
            return null;
        }

        UUID scheduleId = null;
        UUID taskId = null;
        UUID id = null;
        LocalDateTime startedAt = null;
        LocalDateTime endedAt = null;
        Long duration = null;
        LocalDateTime pausedAt = null;
        Long pausedSeconds = null;
        ProgressStatus status = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        scheduleId = focusSessionScheduleId( focusSession );
        taskId = focusSessionTaskId( focusSession );
        id = focusSession.getId();
        startedAt = focusSession.getStartedAt();
        endedAt = focusSession.getEndedAt();
        duration = focusSession.getDuration();
        pausedAt = focusSession.getPausedAt();
        pausedSeconds = focusSession.getPausedSeconds();
        status = focusSession.getStatus();
        createdAt = focusSession.getCreatedAt();
        updatedAt = focusSession.getUpdatedAt();

        FocusSessionResponseDto focusSessionResponseDto = new FocusSessionResponseDto( id, startedAt, endedAt, duration, pausedAt, pausedSeconds, status, scheduleId, taskId, createdAt, updatedAt );

        return focusSessionResponseDto;
    }

    @Override
    public void updateFocusSession(UpdateFocusSessionDto dto, FocusSession focusSession) {
        if ( dto == null ) {
            return;
        }

        if ( dto.endedAt() != null ) {
            focusSession.setEndedAt( dto.endedAt() );
        }
        if ( dto.startedAt() != null ) {
            focusSession.setStartedAt( dto.startedAt() );
        }
        if ( dto.status() != null ) {
            focusSession.setStatus( dto.status() );
        }
    }

    private UUID focusSessionScheduleId(FocusSession focusSession) {
        Schedule schedule = focusSession.getSchedule();
        if ( schedule == null ) {
            return null;
        }
        return schedule.getId();
    }

    private UUID focusSessionTaskId(FocusSession focusSession) {
        Task task = focusSession.getTask();
        if ( task == null ) {
            return null;
        }
        return task.getId();
    }
}
