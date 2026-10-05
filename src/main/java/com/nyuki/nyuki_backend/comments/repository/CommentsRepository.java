package com.nyuki.nyuki_backend.comments.repository;

import com.nyuki.nyuki_backend.comments.entity.Comment;
import com.nyuki.nyuki_backend.tasks.entity.Task;
import com.nyuki.nyuki_backend.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByTaskOrderByCreatedAtAsc(Task task);
    Optional<Comment> findByIdAndAuthorEmail(UUID id, String email);
}
