package com.nyuki.nyuki_backend.schedule.mapper;

import com.nyuki.nyuki_backend.schedule.dto.CreateScheduleDto;
import com.nyuki.nyuki_backend.schedule.dto.ScheduleResponseDto;
import com.nyuki.nyuki_backend.schedule.dto.UpdateScheduleDto;
import com.nyuki.nyuki_backend.schedule.entity.Schedule;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    Schedule toSchedule(CreateScheduleDto dto);

    @Mapping(source = "task.id", target = "taskId")
    ScheduleResponseDto toDto(Schedule schedule);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "task", ignore = true)
    void updateSchedule(UpdateScheduleDto dto, @MappingTarget Schedule schedule);
}
