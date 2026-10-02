package com.nyuki.nyuki_backend.strategies.entity;

import com.nyuki.nyuki_backend.common.baseentity.BaseEntity;
import com.nyuki.nyuki_backend.common.enums.Priority;
import com.nyuki.nyuki_backend.common.enums.ProgressStatus;
import com.nyuki.nyuki_backend.goals.entity.Goal;
import com.nyuki.nyuki_backend.users.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Strategy extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users owner;
    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;
}
