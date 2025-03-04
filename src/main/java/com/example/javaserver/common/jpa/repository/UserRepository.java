package com.example.javaserver.common.jpa.repository;

import com.example.javaserver.common.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
