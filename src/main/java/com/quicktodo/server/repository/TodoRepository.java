package com.quicktodo.server.repository;

import com.quicktodo.server.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findByUserIdAndLastModifiedAfter(Long userId, LocalDateTime lastSyncTime);
    TodoItem findFirstByUserIdOrderByLastModifiedDesc(Long userId);
}
