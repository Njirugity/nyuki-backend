package com.nyuki.nyuki_backend.analytics.service.impl;

import com.nyuki.nyuki_backend.analytics.dto.*;
import com.nyuki.nyuki_backend.analytics.service.AnalyticsService;
import com.nyuki.nyuki_backend.goals.repository.GoalsRepository;
import com.nyuki.nyuki_backend.schedule.repository.ScheduleRepository;
import com.nyuki.nyuki_backend.tasks.repository.TasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {
    private final GoalsRepository goalsRepository;
    private final TasksRepository tasksRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public GoalCountDto getGoalsCount(String email, LocalDate today){
        return goalsRepository.findTotalActiveCompletedOverDueGoals(email, today).
                orElse(new GoalCountDto(0L, 0L, 0L, 0L));
    }

    @Override
    public ActiveAndOverdueGoalsDto getActiveAndOverdueGoals(String email){
        LocalDate today = LocalDate.now();
        List<GoalSummaryDto> relevantGoals = goalsRepository.findActiveAndOverdueGoals(email, today);

        List<GoalSummaryDto> active = new ArrayList<>();
        List<GoalSummaryDto> overdue = new ArrayList<>();
        for(GoalSummaryDto dto : relevantGoals){
            if(dto.endDate() != null && dto.endDate().isBefore(today)){
                overdue.add(dto);
            }else{
                active.add(dto);
            }
        }
        return new ActiveAndOverdueGoalsDto(active, overdue);
    }
    @Override
    public List<TaskCountByGoal> getTaskCountByGoal(String email){
        LocalDate today = LocalDate.now();
        return goalsRepository.findAllTaskCounts(email, today);
    }
    @Override
    public ActiveAndOverdueTasksDto getActiveAndOverdueTasks(String email, LocalDate today){
        List<TaskSummaryDto> relevantTasks = tasksRepository.findActiveAndOverdueTask(email, today);

        List<TaskSummaryDto> active = new ArrayList<>();
        List<TaskSummaryDto> overdue = new ArrayList<>();

        for(TaskSummaryDto dto : relevantTasks){
            if(dto.dueDate() != null && dto.dueDate().isBefore(today)){
                overdue.add(dto);
            }else {
                active.add(dto);
            }
        }
        return new ActiveAndOverdueTasksDto(active, overdue);
    }
    @Override
    public TaskCountDto getTaskCount(String email, LocalDate today){
        return tasksRepository.countAllCompletedActiveOverDueTasks(email,today).
                orElse(new TaskCountDto(0L, 0L, 0L, 0L));
    }
    @Override
    public List<UpcomingScheduleDto> getAlmostDueSchedules(String email, LocalDateTime now, LocalDateTime windowEnd){
        return scheduleRepository.findUpcomingSchedulesWithinWindow(email, now, windowEnd);
    }

    @Override
    public DashboardResponseDto getDashboardResponse(String email){
        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime windowEnd = now.plusHours(1);

        return new DashboardResponseDto(
                getActiveAndOverdueTasks(email, today),
                getGoalsCount(email, today),
                getTaskCount(email, today),
                getAlmostDueSchedules(email, now, windowEnd)
        );
    }
}
