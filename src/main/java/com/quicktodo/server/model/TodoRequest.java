package com.quicktodo.server.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

public class TodoRequest {
    @Getter
    @Setter
    private UUID id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private LocalDateTime dueDate;

    @Getter
    @Setter
    private long userId;

    @Getter
    @Setter
    private TodoItem.Priority priority;

    @Getter
    @Setter
    private String tag;

    @Getter
    @Setter
    private boolean completed;

    @Getter
    @Setter
    private LocalDateTime lastModified;

    @Getter
    @Setter
    private boolean deleted;
}

