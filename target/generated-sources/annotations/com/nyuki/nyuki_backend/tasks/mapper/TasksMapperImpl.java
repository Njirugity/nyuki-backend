package com.nyuki.nyuki_backend.tasks.mapper;

import com.nyuki.nyuki_backend.common.enums.Priority;
import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import com.nyuki.nyuki_backend.goals.entity.Goal;
import com.nyuki.nyuki_backend.strategies.entity.Strategy;
import com.nyuki.nyuki_backend.tasks.dto.CreateTaskDto;
import com.nyuki.nyuki_backend.tasks.dto.TaskResponseDto;
import com.nyuki.nyuki_backend.tasks.dto.UpdateTaskDto;
import com.nyuki.nyuki_backend.tasks.entity.Task;
import java.time.LocalDate;
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
public class TasksMapperImpl implements TasksMapper {

    @Override
    public Task toTask(CreateTaskDto dto) {
        if ( dto == null ) {
            return null;
        }

        Task task = new Task();

        task.setDescription( dto.description() );
        task.setDueDate( dto.dueDate() );
        task.setPriority( dto.priority() );
        task.setStartDate( dto.startDate() );
        task.setStatus( dto.status() );
        task.setTitle( dto.title() );

        return task;
    }

    @Override
    public TaskResponseDto toDto(Task task) {
        if ( task == null ) {
            return null;
        }

        UUID goalId = null;
        UUID strategyId = null;
        UUID id = null;
        String title = null;
        String description = null;
        LocalDate startDate = null;
        LocalDate dueDate = null;
        LocalDate completedAt = null;
        ProgressStatus status = null;
        Priority priority = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        goalId = taskGoalId( task );
        strategyId = taskStrategyId( task );
        id = task.getId();
        title = task.getTitle();
        description = task.getDescription();
        startDate = task.getStartDate();
        dueDate = task.getDueDate();
        completedAt = task.getCompletedAt();
        status = task.getStatus();
        priority = task.getPriority();
        createdAt = task.getCreatedAt();
        updatedAt = task.getUpdatedAt();

        TaskResponseDto taskResponseDto = new TaskResponseDto( id, title, description, startDate, dueDate, completedAt, status, priority, goalId, strategyId, createdAt, updatedAt );

        return taskResponseDto;
    }

    @Override
    public void updateTask(UpdateTaskDto dto, Task task) {
        if ( dto == null ) {
            return;
        }

        if ( dto.description() != null ) {
            task.setDescription( dto.description() );
        }
        if ( dto.dueDate() != null ) {
            task.setDueDate( dto.dueDate() );
        }
        if ( dto.priority() != null ) {
            task.setPriority( dto.priority() );
        }
        if ( dto.startDate() != null ) {
            task.setStartDate( dto.startDate() );
        }
        if ( dto.status() != null ) {
            task.setStatus( dto.status() );
        }
        if ( dto.title() != null ) {
            task.setTitle( dto.title() );
        }
    }

    private UUID taskGoalId(Task task) {
        Goal goal = task.getGoal();
        if ( goal == null ) {
            return null;
        }
        return goal.getId();
    }

    private UUID taskStrategyId(Task task) {
        Strategy strategy = task.getStrategy();
        if ( strategy == null ) {
            return null;
        }
        return strategy.getId();
    }
}
