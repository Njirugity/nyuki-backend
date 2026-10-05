package com.nyuki.nyuki_backend.focussession.service;

import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import com.nyuki.nyuki_backend.focussession.dto.CreateFocusSessionDto;
import com.nyuki.nyuki_backend.focussession.dto.FocusSessionResponseDto;
import com.nyuki.nyuki_backend.focussession.dto.UpdateFocusSessionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FocusSessionService {
    FocusSessionResponseDto create(CreateFocusSessionDto dto, String email);
    FocusSessionResponseDto getFocusSession(UUID focusSessionId, String email);
    Page<FocusSessionResponseDto> getFocusSessions(String email, UUID scheduleId, UUID taskId,
                                                   ProgressStatus status, Pageable pageable);
    FocusSessionResponseDto update(UUID focusSessionId, UpdateFocusSessionDto dto, String email);
    void delete(UUID focusSessionId, String email);
    FocusSessionResponseDto pause(UUID focusSessionId, String email);
    FocusSessionResponseDto resume(UUID focusSessionId, String email);
    FocusSessionResponseDto stop(UUID focusSessionId, String email);
}
