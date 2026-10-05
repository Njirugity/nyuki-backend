package com.nyuki.nyuki_backend.schedule.mapper;

import com.nyuki.nyuki_backend.schedule.dto.CreateScheduleDto;
import com.nyuki.nyuki_backend.schedule.dto.ScheduleResponseDto;
import com.nyuki.nyuki_backend.schedule.dto.UpdateScheduleDto;
import com.nyuki.nyuki_backend.schedule.entity.Schedule;
import com.nyuki.nyuki_backend.tasks.entity.Task;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-05T17:34:57+0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.100.v20260826-1225, environment: Java 21.0.12.1 (Eclipse Adoptium)"
)
@Component
public class ScheduleMapperImpl implements ScheduleMapper {

    @Override
    public Schedule toSchedule(CreateScheduleDto dto) {
        if ( dto == null ) {
            return null;
        }

        Schedule schedule = new Schedule();

        schedule.setEndDateTime( dto.endDateTime() );
        schedule.setNotes( dto.notes() );
        schedule.setStartDateTime( dto.startDateTime() );

        return schedule;
    }

    @Override
    public ScheduleResponseDto toDto(Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }

        UUID taskId = null;
        UUID id = null;
        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null;
        String notes = null;

        taskId = scheduleTaskId( schedule );
        id = schedule.getId();
        startDateTime = schedule.getStartDateTime();
        endDateTime = schedule.getEndDateTime();
        notes = schedule.getNotes();

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto( id, startDateTime, endDateTime, notes, taskId );

        return scheduleResponseDto;
    }

    @Override
    public void updateSchedule(UpdateScheduleDto dto, Schedule schedule) {
        if ( dto == null ) {
            return;
        }

        if ( dto.endDateTime() != null ) {
            schedule.setEndDateTime( dto.endDateTime() );
        }
        if ( dto.notes() != null ) {
            schedule.setNotes( dto.notes() );
        }
        if ( dto.startDateTime() != null ) {
            schedule.setStartDateTime( dto.startDateTime() );
        }
    }

    private UUID scheduleTaskId(Schedule schedule) {
        Task task = schedule.getTask();
        if ( task == null ) {
            return null;
        }
        return task.getId();
    }
}
