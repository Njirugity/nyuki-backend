package com.nyuki.nyuki_backend.goals.repository;

import com.nyuki.nyuki_backend.analytics.dto.GoalCountDto;
import com.nyuki.nyuki_backend.analytics.dto.GoalSummaryDto;
import com.nyuki.nyuki_backend.analytics.dto.TaskCountByGoal;
import com.nyuki.nyuki_backend.goals.entity.Goal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GoalsRepository extends JpaRepository<Goal, UUID> {

    Optional<Goal> findByIdAndOwnerEmail(UUID id, String email);

    // CAST gives a null :search a type; otherwise Postgres infers bytea and LOWER() fails.
    // Priority is stored as a string, so order by its rank rather than alphabetically
    @Query(value = """
            SELECT g FROM Goal g
            WHERE g.owner.email = :email
              AND (CAST(:search AS String) IS NULL
                   OR LOWER(g.title) LIKE LOWER(CONCAT('%', CAST(:search AS String), '%'))
                   OR LOWER(g.description) LIKE LOWER(CONCAT('%', CAST(:search AS String), '%')))
            ORDER BY CASE g.priority
                       WHEN com.nyuki.nyuki_backend.common.enums.Priority.HIGH THEN 0
                       WHEN com.nyuki.nyuki_backend.common.enums.Priority.MEDIUM THEN 1
                       WHEN com.nyuki.nyuki_backend.common.enums.Priority.LOW THEN 2
                       ELSE 3
                     END,
                     g.createdAt DESC
            """,
            countQuery = """
            SELECT COUNT(g) FROM Goal g
            WHERE g.owner.email = :email
              AND (CAST(:search AS String) IS NULL
                   OR LOWER(g.title) LIKE LOWER(CONCAT('%', CAST(:search AS String), '%'))
                   OR LOWER(g.description) LIKE LOWER(CONCAT('%', CAST(:search AS String), '%')))
            """)
    Page<Goal> searchByOwner(@Param("email") String email, @Param("search") String search, Pageable pageable);

    @Query("""
        SELECT new com.nyuki.nyuki_backend.analytics.dto.TaskCountByGoal(
            g.id,
            g.title,
            g.priority,
            COUNT(t),
            COALESCE(SUM(CASE WHEN t.status = com.nyuki.nyuki_backend.common.enums.ProgressStatus.COMPLETED THEN 1 ELSE 0 END ),0L),
            COALESCE(SUM(CASE WHEN t.status = com.nyuki.nyuki_backend.common.enums.ProgressStatus.ACTIVE THEN 1 ELSE 0 END ),0L),
            COALESCE(SUM(CASE WHEN t.dueDate < :today AND t.status != com.nyuki.nyuki_backend.common.enums.ProgressStatus.COMPLETED THEN 1 ELSE 0 END ),0L)
            ) FROM Goal g
        LEFT JOIN Task t ON t.goal.id = g.id
        WHERE g.owner.email = :email
        GROUP BY g.id, g.title
    """)
    List<TaskCountByGoal> findAllTaskCounts(@Param("email") String email, @Param("today")LocalDate today);

    @Query("""
        SELECT new com.nyuki.nyuki_backend.analytics.dto.GoalCountDto(
            COUNT(g),
            COALESCE(SUM(CASE WHEN g.status = com.nyuki.nyuki_backend.common.enums.ProgressStatus.COMPLETED THEN 1 ELSE 0 END),0L),
            COALESCE(SUM(CASE WHEN g.status = com.nyuki.nyuki_backend.common.enums.ProgressStatus.ACTIVE THEN 1 ELSE 0 END),0L),
            COALESCE(SUM(CASE WHEN g.endDate < :today AND g.status != com.nyuki.nyuki_backend.common.enums.ProgressStatus.COMPLETED THEN 1 ELSE 0 END), 0L)
            ) FROM Goal g
        WHERE g.owner.email = :email
    """)
    Optional<GoalCountDto> findTotalActiveCompletedOverDueGoals(@Param("email") String email, @Param("today")LocalDate today);

    @Query("""
        SELECT new com.nyuki.nyuki_backend.analytics.dto.GoalSummaryDto(
            g.id, g.title, g.endDate, g.status, g.priority
            ) FROM Goal g
         WHERE g.owner.email = :email
         AND g.status IN(
                  com.nyuki.nyuki_backend.common.enums.ProgressStatus.NOT_STARTED,
                  com.nyuki.nyuki_backend.common.enums.ProgressStatus.ACTIVE,
                  com.nyuki.nyuki_backend.common.enums.ProgressStatus.PAUSED
             )
         AND COALESCE(g.startDate, g.endDate) <= :today
         ORDER BY g.endDate ASC
    """)
    List<GoalSummaryDto> findActiveAndOverdueGoals(@Param("email") String email, @Param("today")LocalDate today);
}
