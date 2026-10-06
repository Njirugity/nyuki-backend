package com.nyuki.nyuki_backend.analytics.dto;

import java.util.List;

public record ActiveAndOverdueGoalsDto(
        List<GoalSummaryDto> activeGoals,
        List<GoalSummaryDto> overDueGoals
) {
}
