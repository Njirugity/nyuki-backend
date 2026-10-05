package com.nyuki.nyuki_backend.todolist.repository;

import com.nyuki.nyuki_backend.todolist.entity.ToDoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, UUID> {

    Optional<ToDoList> findByIdAndOwnerEmail(UUID id, String email);

    // CASTs give null parameters a type; otherwise Postgres can't infer them
    @Query(value = """
            SELECT l FROM ToDoList l
            WHERE l.owner.email = :email
              AND (CAST(:taskId AS String) IS NULL OR l.task.id = :taskId)
              AND (CAST(:search AS String) IS NULL
                   OR LOWER(l.title) LIKE LOWER(CONCAT('%', CAST(:search AS String), '%')))
            ORDER BY l.createdAt DESC
            """,
            countQuery = """
            SELECT COUNT(l) FROM ToDoList l
            WHERE l.owner.email = :email
              AND (CAST(:taskId AS String) IS NULL OR l.task.id = :taskId)
              AND (CAST(:search AS String) IS NULL
                   OR LOWER(l.title) LIKE LOWER(CONCAT('%', CAST(:search AS String), '%')))
            """)
    Page<ToDoList> searchByOwner(@Param("email") String email, @Param("search") String search,
                                 @Param("taskId") UUID taskId, Pageable pageable);
}
