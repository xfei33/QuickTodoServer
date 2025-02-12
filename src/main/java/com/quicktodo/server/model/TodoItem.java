package com.quicktodo.server.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "todos", indexes = {
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_due_date", columnList = "due_date")
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "user")
public class TodoItem {
    public enum Priority { LOW, MEDIUM, HIGH }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(nullable = false)
    private String tag;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean completed = false;

    @Column(name = "last_modified", nullable = false)
    private LocalDateTime lastModified = LocalDateTime.now(); // 最后修改时间
}
