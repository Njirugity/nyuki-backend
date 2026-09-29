package com.nyuki.nyuki_backend.todolist.repository;

import com.nyuki.nyuki_backend.todolist.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
}
