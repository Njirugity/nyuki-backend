package com.nyuki.nyuki_backend.tasks.repository;

import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import com.nyuki.nyuki_backend.analytics.dto.TaskCountDto;
import com.nyuki.nyuki_backend.analytics.dto.TaskSummaryDto;
import com.nyuki.nyuki_backend.goals.entity.Goal;
import com.nyuki.nyuki_backend.strategies.entity.Strategy;
import com.nyuki.nyuki_backend.tasks.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TasksRepository extends JpaRepository<Task, UUID> {

    Optional<Task> findByIdAndOwnerEmail(UUID id, String email);

    @Modifying
    @Query("UPDATE Task t SET t.goal = :goal WHERE t.strategy = :strategy")
    void updateGoalForStrategy(@Param("strategy") Strategy strategy, @Param("goal") Goal goal);

    @Query(value = """
            SELECT t FROM Task t
            WHERE t.owner.email = :email
              AND (CAST(:goalId AS String) IS NULL OR t.goal.id = :goalId)
              AND (CAST(:strategyId AS String) IS NULL OR t.strategy.id = :strategyId)
              AND (CAST(:status AS String) IS NULL OR t.status = :status)
              AND (CAST(:search AS String) IS NULL
                   OR LOWER(t.title) LIKE LOWER(CONCAT('%', CAST(:search AS String), '%'))
                   OR LOWER(t.description) LIKE LOWER(CONCAT('%', CAST(:search AS String), '%')))
            ORDER BY CASE t.priority
                       WHEN com.nyuki.nyuki_backend.common.enums.Priority.HIGH THEN 0
                       WHEN com.nyuki.nyuki_backend.common.enums.Priority.MEDIUM THEN 1
                       WHEN com.nyuki.nyuki_backend.common.enums.Priority.LOW THEN 2
                       ELSE 3
                     END,
                     t.dueDate ASC NULLS LAST,
                     t.createdAt DESC
            """,
            countQuery = """
            SELECT COUNT(t) FROM Task t
            WHERE t.owner.email = :email
              AND (CAST(:goalId AS String) IS NULL OR t.goal.id = :goalId)
              AND (CAST(:strategyId AS String) IS NULL OR t.strategy.id = :strategyId)
              AND (CAST(:status AS String) IS NULL OR t.status = :status)
              AND (CAST(:search AS String) IS NULL
                   OR LOWER(t.title) LIKE LOWER(CONCAT('%', CAST(:search AS String), '%'))
                   OR LOWER(t.description) LIKE LOWER(CONCAT('%', CAST(:search AS String), '%')))
            """)
    Page<Task> searchByOwner(@Param("email") String email, @Param("search") String search,
                             @Param("goalId") UUID goalId, @Param("strategyId") UUID strategyId,
                             @Param("status") ProgressStatus status, Pageable pageable);

    @Query("""
        SELECT new com.nyuki.nyuki_backend.analytics.dto.TaskSummaryDto(
            t.id, t.title, t.dueDate, t.status, g.title
            ) FROM Task t
        LEFT JOIN t.goal g
        WHERE t.owner.email = :email
        AND t.status IN (
                  com.nyuki.nyuki_backend.common.enums.ProgressStatus.NOT_STARTED,
                  com.nyuki.nyuki_backend.common.enums.ProgressStatus.ACTIVE,
                  com.nyuki.nyuki_backend.common.enums.ProgressStatus.PAUSED
              )
        AND COALESCE(t.startDate, t.dueDate) <= :today
        ORDER BY t.dueDate ASC
    """)
    List<TaskSummaryDto> findActiveAndOverdueTask(@Param("email")String email, @Param("today") LocalDate today);

    @Query("""
        SELECT new com.nyuki.nyuki_backend.analytics.dto.TaskCountDto(
            COUNT(t),
            COALESCE(SUM(CASE WHEN t.status = com.nyuki.nyuki_backend.common.enums.ProgressStatus.COMPLETED THEN 1 ELSE 0 END ),0L),
            COALESCE(SUM(CASE WHEN t.status = com.nyuki.nyuki_backend.common.enums.ProgressStatus.ACTIVE THEN 1 ELSE 0 END ),0L),
            COALESCE(SUM(CASE WHEN t.dueDate < :today AND t.status != com.nyuki.nyuki_backend.common.enums.ProgressStatus.COMPLETED THEN 1 ELSE 0 END ),0L)
            ) FROM Task t
        WHERE t.owner.email = :email
    """)
    Optional<TaskCountDto> countAllCompletedActiveOverDueTasks(@Param("email")String email, @Param("today") LocalDate today);


}
