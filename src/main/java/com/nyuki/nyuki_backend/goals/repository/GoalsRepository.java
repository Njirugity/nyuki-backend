package com.nyuki.nyuki_backend.goals.repository;

import com.nyuki.nyuki_backend.goals.entity.Goal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
