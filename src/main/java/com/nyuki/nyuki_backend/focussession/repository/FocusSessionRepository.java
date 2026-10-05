package com.nyuki.nyuki_backend.focussession.repository;

import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import com.nyuki.nyuki_backend.focussession.entity.FocusSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FocusSessionRepository extends JpaRepository<FocusSession, UUID> {

    Optional<FocusSession> findByIdAndOwnerEmail(UUID id, String email);

    // CASTs give null parameters a type; otherwise Postgres can't infer them
    @Query(value = """
            SELECT f FROM FocusSession f
            WHERE f.owner.email = :email
              AND (CAST(:scheduleId AS String) IS NULL OR f.schedule.id = :scheduleId)
              AND (CAST(:taskId AS String) IS NULL OR f.task.id = :taskId)
              AND (CAST(:status AS String) IS NULL OR f.status = :status)
            ORDER BY f.startedAt DESC
            """,
            countQuery = """
            SELECT COUNT(f) FROM FocusSession f
            WHERE f.owner.email = :email
              AND (CAST(:scheduleId AS String) IS NULL OR f.schedule.id = :scheduleId)
              AND (CAST(:taskId AS String) IS NULL OR f.task.id = :taskId)
              AND (CAST(:status AS String) IS NULL OR f.status = :status)
            """)
    Page<FocusSession> searchByOwner(@Param("email") String email, @Param("scheduleId") UUID scheduleId,
                                     @Param("taskId") UUID taskId, @Param("status") ProgressStatus status,
                                     Pageable pageable);
}
