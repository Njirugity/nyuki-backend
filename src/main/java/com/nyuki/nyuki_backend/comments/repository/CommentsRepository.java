package com.nyuki.nyuki_backend.comments.repository;

import com.nyuki.nyuki_backend.comments.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {
}
