package com.nyuki.nyuki_backend.schedule.repository;

import com.nyuki.nyuki_backend.analytics.dto.UpcomingScheduleDto;
import com.nyuki.nyuki_backend.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    Optional<Schedule> findByIdAndOwnerEmail(UUID id, String email);
    @Query(value = """
        SELECT s FROM Schedule s
        WHERE s.owner.email = :email
        AND(CAST(:taskId AS String) IS NULL OR s.task.id = :taskId)
    """,
    countQuery = """
            SELECT COUNT(s) FROM Schedule s
            WHERE s.owner.email = :email
            AND(CAST(:taskId AS String) IS NULL OR s.task.id = :taskId)
            """)
    Page<Schedule> listSchedules(@Param("email") String email, @Param("taskId") UUID taskId,
                                 Pageable pageable
    );
    @Query("""
        SELECT new com.nyuki.nyuki_backend.analytics.dto.UpcomingScheduleDto(
            s.id, s.startDateTime, t.title, g.title) FROM Schedule s
        JOIN s.task t
        LEFT JOIN t.goal g
        WHERE s.owner.email = :email
        AND s.startDateTime BETWEEN :now AND :windowEnd
        AND t.status != com.nyuki.nyuki_backend.common.enums.ProgressStatus.COMPLETED
        ORDER BY s.startDateTime ASC
    """)
    List<UpcomingScheduleDto> findUpcomingSchedulesWithinWindow(
            @Param("email") String email,
            @Param("now") Instant now,
            @Param("windowEnd") Instant windowEnd
    );
}
