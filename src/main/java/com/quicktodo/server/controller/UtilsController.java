package com.quicktodo.server.controller;

import com.quicktodo.server.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/utils")
public class UtilsController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/latestTime")
    public ResponseEntity<LocalDateTime> getLatestTime(
            @RequestParam Long userId
    ) {
        return ResponseEntity.ok(todoService.getLatestTodoItem(userId));
    }
}
