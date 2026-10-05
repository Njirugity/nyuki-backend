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
    date = "2026-10-05T17:34:55+0300",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.100.v20260826-1225, environment: Java 21.0.12.1 (Eclipse Adoptium)"
)
@Component
public class GoalsMapperImpl implements GoalsMapper {

    @Override
    public Goal toGoal(CreateGoalDto dto) {
        if ( dto == null ) {
            return null;
        }

        Goal goal = new Goal();

        goal.setDescription( dto.description() );
        goal.setEndDate( dto.endDate() );
        goal.setPriority( dto.priority() );
        goal.setStartDate( dto.startDate() );
        goal.setStatus( dto.status() );
        goal.setTitle( dto.title() );

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

        if ( dto.description() != null ) {
            goal.setDescription( dto.description() );
        }
        if ( dto.endDate() != null ) {
            goal.setEndDate( dto.endDate() );
        }
        if ( dto.priority() != null ) {
            goal.setPriority( dto.priority() );
        }
        if ( dto.startDate() != null ) {
            goal.setStartDate( dto.startDate() );
        }
        if ( dto.status() != null ) {
            goal.setStatus( dto.status() );
        }
        if ( dto.title() != null ) {
            goal.setTitle( dto.title() );
        }
    }
}
