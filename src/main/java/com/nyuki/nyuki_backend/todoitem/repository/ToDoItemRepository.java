package com.nyuki.nyuki_backend.todoitem.repository;

import com.nyuki.nyuki_backend.todoitem.entity.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
}
