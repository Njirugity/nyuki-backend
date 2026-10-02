package com.nyuki.nyuki_backend.goals.mapper;

import com.nyuki.nyuki_backend.common.enums.Priority;
import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import com.nyuki.nyuki_backend.goals.dto.CreateGoalDto;
import com.nyuki.nyuki_backend.goals.dto.GoalResponseDto;
import com.nyuki.nyuki_backend.goals.dto.UpdateGoalDto;
import com.nyuki.nyuki_backend.goals.entity.Goal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-01T23:51:52+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Ubuntu)"
)
@Component
public class GoalsMapperImpl implements GoalsMapper {

    @Override
    public Goal toGoal(CreateGoalDto dto) {
        if ( dto == null ) {
            return null;
        }

        Goal goal = new Goal();

        goal.setTitle( dto.title() );
        goal.setDescription( dto.description() );
        goal.setStartDate( dto.startDate() );
        goal.setEndDate( dto.endDate() );
        goal.setStatus( dto.status() );
        goal.setPriority( dto.priority() );

        return goal;
    }

    @Override
    public GoalResponseDto toDto(Goal goal) {
        if ( goal == null ) {
            return null;
        }

        UUID id = null;
        String title = null;
        String description = null;
        LocalDate startDate = null;
        LocalDate endDate = null;
        ProgressStatus status = null;
        Priority priority = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        id = goal.getId();
        title = goal.getTitle();
        description = goal.getDescription();
        startDate = goal.getStartDate();
        endDate = goal.getEndDate();
        status = goal.getStatus();
        priority = goal.getPriority();
        createdAt = goal.getCreatedAt();
        updatedAt = goal.getUpdatedAt();

        GoalResponseDto goalResponseDto = new GoalResponseDto( id, title, description, startDate, endDate, status, priority, createdAt, updatedAt );

        return goalResponseDto;
    }

    @Override
    public void updateGoal(UpdateGoalDto dto, Goal goal) {
        if ( dto == null ) {
            return;
        }

        if ( dto.title() != null ) {
            goal.setTitle( dto.title() );
        }
        if ( dto.description() != null ) {
            goal.setDescription( dto.description() );
        }
        if ( dto.startDate() != null ) {
            goal.setStartDate( dto.startDate() );
        }
        if ( dto.endDate() != null ) {
            goal.setEndDate( dto.endDate() );
        }
        if ( dto.status() != null ) {
            goal.setStatus( dto.status() );
        }
        if ( dto.priority() != null ) {
            goal.setPriority( dto.priority() );
        }
    }
}
