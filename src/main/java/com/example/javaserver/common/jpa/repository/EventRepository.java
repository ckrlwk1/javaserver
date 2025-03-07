package com.example.javaserver.common.jpa.repository;

import com.example.javaserver.common.jpa.entity.EventEntity;
import com.example.javaserver.common.jpa.entity.UserEntity;
import jdk.jfr.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    List<EventEntity> findByUserId(String userId);
}
