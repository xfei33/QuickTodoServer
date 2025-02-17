package com.quicktodo.server.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "todos", indexes = {
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_due_date", columnList = "due_date"),
        @Index(name = "idx_priority", columnList = "priority")
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TodoItem {
    public enum Priority { LOW, MEDIUM, HIGH }

    @Id
    @EqualsAndHashCode.Include
    private UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(nullable = false)
    private String tag;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean completed = false;

    @Column(name = "last_modified", nullable = false)
    private LocalDateTime lastModified = LocalDateTime.now(); // 最后修改时间

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean deleted = false; // 是否删除

    @PreUpdate
    public void preUpdate() {
        lastModified = LocalDateTime.now();
    }
}
