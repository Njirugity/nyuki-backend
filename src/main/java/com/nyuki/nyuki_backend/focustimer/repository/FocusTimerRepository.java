package com.nyuki.nyuki_backend.focustimer.repository;

import com.nyuki.nyuki_backend.focustimer.entity.FocusTimer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FocusTimerRepository extends JpaRepository<FocusTimer, Long> {
}
