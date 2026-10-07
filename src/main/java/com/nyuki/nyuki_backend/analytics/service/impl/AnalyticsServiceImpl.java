package com.nyuki.nyuki_backend.analytics.service.impl;

import com.nyuki.nyuki_backend.analytics.dto.*;
import com.nyuki.nyuki_backend.analytics.service.AnalyticsService;
import com.nyuki.nyuki_backend.goals.repository.GoalsRepository;
import com.nyuki.nyuki_backend.schedule.repository.ScheduleRepository;
import com.nyuki.nyuki_backend.tasks.repository.TasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {
    private final GoalsRepository goalsRepository;
    private final TasksRepository tasksRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public GoalCountDto getGoalsCount(String email, Instant now){
        return goalsRepository.findTotalActiveCompletedOverDueGoals(email, now).
                orElse(new GoalCountDto(0L, 0L, 0L, 0L));
    }

    @Override
    public ActiveAndOverdueGoalsDto getActiveAndOverdueGoals(String email){
        Instant now = Instant.now();
        List<GoalSummaryDto> relevantGoals = goalsRepository.findActiveAndOverdueGoals(email, now);

        List<GoalSummaryDto> active = new ArrayList<>();
        List<GoalSummaryDto> overdue = new ArrayList<>();
        for(GoalSummaryDto dto : relevantGoals){
            if(dto.endDate() != null && dto.endDate().isBefore(now)){
                overdue.add(dto);
            }else{
                active.add(dto);
            }
        }
        return new ActiveAndOverdueGoalsDto(active, overdue);
    }
    @Override
    public List<TaskCountByGoal> getTaskCountByGoal(String email){
        Instant now = Instant.now();
        return goalsRepository.findAllTaskCounts(email, now);
    }
    @Override
    public ActiveAndOverdueTasksDto getActiveAndOverdueTasks(String email, Instant now){
        List<TaskSummaryDto> relevantTasks = tasksRepository.findActiveAndOverdueTask(email, now);

        List<TaskSummaryDto> active = new ArrayList<>();
        List<TaskSummaryDto> overdue = new ArrayList<>();

        for(TaskSummaryDto dto : relevantTasks){
            if(dto.dueDate() != null && dto.dueDate().isBefore(now)){
                overdue.add(dto);
            }else {
                active.add(dto);
            }
        }
        return new ActiveAndOverdueTasksDto(active, overdue);
    }
    @Override
    public TaskCountDto getTaskCount(String email, Instant now){
        return tasksRepository.countAllCompletedActiveOverDueTasks(email,now).
                orElse(new TaskCountDto(0L, 0L, 0L, 0L));
    }
    @Override
    public List<UpcomingScheduleDto> getAlmostDueSchedules(String email, Instant now, Instant windowEnd){
        return scheduleRepository.findUpcomingSchedulesWithinWindow(email, now, windowEnd);
    }

    @Override
    public DashboardResponseDto getDashboardResponse(String email){
        Instant now = Instant.now();
        Instant windowEnd = now.plus(1, ChronoUnit.HOURS);

        return new DashboardResponseDto(
                getActiveAndOverdueTasks(email, now),
                getGoalsCount(email, now),
                getTaskCount(email, now),
                getAlmostDueSchedules(email, now, windowEnd)
        );
    }
}
