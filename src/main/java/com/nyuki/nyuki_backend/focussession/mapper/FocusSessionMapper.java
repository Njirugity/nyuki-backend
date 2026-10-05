package com.nyuki.nyuki_backend.focussession.mapper;

import com.nyuki.nyuki_backend.focussession.dto.CreateFocusSessionDto;
import com.nyuki.nyuki_backend.focussession.dto.FocusSessionResponseDto;
import com.nyuki.nyuki_backend.focussession.dto.UpdateFocusSessionDto;
import com.nyuki.nyuki_backend.focussession.entity.FocusSession;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface FocusSessionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "duration", ignore = true)
    @Mapping(target = "pausedAt", ignore = true)
    @Mapping(target = "pausedSeconds", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "task", ignore = true)
    @Mapping(target = "schedule", ignore = true)
    @Mapping(target = "status", ignore = true)
    FocusSession toFocusSession(CreateFocusSessionDto dto);

    @Mapping(source = "schedule.id", target = "scheduleId")
    @Mapping(source = "task.id", target = "taskId")
    FocusSessionResponseDto toDto(FocusSession focusSession);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "duration", ignore = true)
    @Mapping(target = "pausedAt", ignore = true)
    @Mapping(target = "pausedSeconds", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "task", ignore = true)
    @Mapping(target = "schedule", ignore = true)
    void updateFocusSession(UpdateFocusSessionDto dto, @MappingTarget FocusSession focusSession);
}
