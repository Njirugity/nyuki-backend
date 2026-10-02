package com.nyuki.nyuki_backend.strategies.repository;

import com.nyuki.nyuki_backend.goals.entity.Goal;
import com.nyuki.nyuki_backend.strategies.entity.Strategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StrategiesRepository extends JpaRepository<Strategy, UUID> {

    Optional<Strategy> findByIdAndOwnerEmail(UUID id, String email);

    void deleteByGoal(Goal goal);

    @Query(value = """
            SELECT s FROM Strategy s
            WHERE s.owner.email = :email
              AND (CAST(:goalId AS String) IS NULL OR s.goal.id = :goalId)
              AND (CAST(:search AS String) IS NULL
                   OR LOWER(s.title) LIKE LOWER(CONCAT('%', CAST(:search AS String), '%'))
                   OR LOWER(s.description) LIKE LOWER(CONCAT('%', CAST(:search AS String), '%')))
            ORDER BY s.createdAt DESC
            """,
            countQuery = """
            SELECT COUNT(s) FROM Strategy s
            WHERE s.owner.email = :email
              AND (CAST(:goalId AS String) IS NULL OR s.goal.id = :goalId)
              AND (CAST(:search AS String) IS NULL
                   OR LOWER(s.title) LIKE LOWER(CONCAT('%', CAST(:search AS String), '%'))
                   OR LOWER(s.description) LIKE LOWER(CONCAT('%', CAST(:search AS String), '%')))
            """)
    Page<Strategy> searchByOwner(@Param("email") String email, @Param("search") String search,
                                 @Param("goalId") UUID goalId, Pageable pageable);
}
