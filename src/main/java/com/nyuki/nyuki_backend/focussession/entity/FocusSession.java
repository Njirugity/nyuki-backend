package com.nyuki.nyuki_backend.focussession.entity;

import com.nyuki.nyuki_backend.common.baseentity.BaseEntity;
import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import com.nyuki.nyuki_backend.schedule.entity.Schedule;
import com.nyuki.nyuki_backend.tasks.entity.Task;
import com.nyuki.nyuki_backend.users.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FocusSession extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Instant startedAt;
    private Instant endedAt;
    private Long duration;
    // Set while the session is paused; cleared on resume or stop
    private Instant pausedAt;
    // Total time spent paused, excluded from the duration
    private Long pausedSeconds;
    @Enumerated(EnumType.STRING)
    private ProgressStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users owner;
    @ManyToOne
    @JoinColumn(name = "task_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Task task;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Schedule schedule;
}
