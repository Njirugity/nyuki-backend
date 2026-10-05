package com.nyuki.nyuki_backend.todoitem.entity;

import com.nyuki.nyuki_backend.common.baseentity.BaseEntity;
import com.nyuki.nyuki_backend.todolist.entity.ToDoList;
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
public class ToDoItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private boolean completed;
    @ManyToOne
    @JoinColumn(name = "to_do_list_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ToDoList toDoList;
}
