package com.nyuki.nyuki_backend.comments.entity;

import com.nyuki.nyuki_backend.common.baseentity.BaseEntity;
import com.nyuki.nyuki_backend.tasks.entity.Task;
import com.nyuki.nyuki_backend.users.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Users author;
    @ManyToOne
    @JoinColumn(name = "task_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Task task;
}
