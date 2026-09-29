package com.nyuki.nyuki_backend.goals.repository;

import com.nyuki.nyuki_backend.goals.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalsRepository extends JpaRepository<Goal, Long> {
}
