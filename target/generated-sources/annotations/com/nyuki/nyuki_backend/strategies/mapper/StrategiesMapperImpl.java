package com.nyuki.nyuki_backend.strategies.mapper;

import com.nyuki.nyuki_backend.goals.entity.Goal;
import com.nyuki.nyuki_backend.strategies.dto.CreateStrategyDto;
import com.nyuki.nyuki_backend.strategies.dto.StrategyResponseDto;
import com.nyuki.nyuki_backend.strategies.dto.UpdateStrategyDto;
import com.nyuki.nyuki_backend.strategies.entity.Strategy;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-02T09:56:36+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Ubuntu)"
)
@Component
public class StrategiesMapperImpl implements StrategiesMapper {

    @Override
    public Strategy toStrategy(CreateStrategyDto dto) {
        if ( dto == null ) {
            return null;
        }

        Strategy strategy = new Strategy();

        strategy.setTitle( dto.title() );
        strategy.setDescription( dto.description() );

        return strategy;
    }

    @Override
    public StrategyResponseDto toDto(Strategy strategy) {
        if ( strategy == null ) {
            return null;
        }

        UUID goalId = null;
        String goalTitle = null;
        UUID id = null;
        String title = null;
        String description = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        goalId = strategyGoalId( strategy );
        goalTitle = strategyGoalTitle( strategy );
        id = strategy.getId();
        title = strategy.getTitle();
        description = strategy.getDescription();
        createdAt = strategy.getCreatedAt();
        updatedAt = strategy.getUpdatedAt();

        StrategyResponseDto strategyResponseDto = new StrategyResponseDto( id, title, description, goalId, goalTitle, createdAt, updatedAt );

        return strategyResponseDto;
    }

    @Override
    public void updateStrategy(UpdateStrategyDto dto, Strategy strategy) {
        if ( dto == null ) {
            return;
        }

        if ( dto.title() != null ) {
            strategy.setTitle( dto.title() );
        }
        if ( dto.description() != null ) {
            strategy.setDescription( dto.description() );
        }
    }

    private UUID strategyGoalId(Strategy strategy) {
        Goal goal = strategy.getGoal();
        if ( goal == null ) {
            return null;
        }
        return goal.getId();
    }

    private String strategyGoalTitle(Strategy strategy) {
        Goal goal = strategy.getGoal();
        if ( goal == null ) {
            return null;
        }
        return goal.getTitle();
    }
}
