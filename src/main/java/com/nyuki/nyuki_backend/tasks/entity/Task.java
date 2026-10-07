package com.nyuki.nyuki_backend.tasks.entity;

import com.nyuki.nyuki_backend.common.baseentity.BaseEntity;
import com.nyuki.nyuki_backend.common.enums.Priority;
import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import com.nyuki.nyuki_backend.goals.entity.Goal;
import com.nyuki.nyuki_backend.strategies.entity.Strategy;
import com.nyuki.nyuki_backend.users.entity.Users;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private Instant startDate;
    private Instant dueDate;
    private Instant completedAt;
    @Enumerated(EnumType.STRING)
    private ProgressStatus status;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users owner;
    @ManyToOne
    @JoinColumn(name = "goal_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Goal goal;
    @ManyToOne
    @JoinColumn(name = "strategy_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Strategy strategy;
}
