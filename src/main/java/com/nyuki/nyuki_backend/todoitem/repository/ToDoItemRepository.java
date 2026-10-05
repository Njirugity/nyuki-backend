package com.nyuki.nyuki_backend.todoitem.repository;

import com.nyuki.nyuki_backend.todoitem.entity.ToDoItem;
import com.nyuki.nyuki_backend.todolist.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, UUID> {

    Optional<ToDoItem> findByIdAndToDoList(UUID id, ToDoList toDoList);

    List<ToDoItem> findByToDoListOrderByCreatedAtAsc(ToDoList toDoList);

    List<ToDoItem> findByToDoListAndCompletedOrderByCreatedAtAsc(ToDoList toDoList, boolean completed);
}
