package com.nyuki.nyuki_backend.auth.repository;

import com.nyuki.nyuki_backend.auth.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
}
