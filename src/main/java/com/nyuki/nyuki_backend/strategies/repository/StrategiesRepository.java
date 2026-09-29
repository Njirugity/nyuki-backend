package com.nyuki.nyuki_backend.strategies.repository;

import com.nyuki.nyuki_backend.strategies.entity.Strategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrategiesRepository extends JpaRepository<Strategy, Long> {
}
