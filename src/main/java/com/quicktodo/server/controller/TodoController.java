package com.quicktodo.server.controller;

import com.quicktodo.server.model.TodoItem;
import com.quicktodo.server.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    // 增量同步：获取服务端的增量数据
    @GetMapping("/sync")
    public ResponseEntity<List<TodoItem>> getIncrementalData(
            @RequestParam Long userId,
            @RequestParam LocalDateTime lastSyncTime) {
        List<TodoItem> incrementalData = todoService.getIncrementalData(userId, lastSyncTime);
        return ResponseEntity.ok(incrementalData);
    }

    // 增量同步：上传客户端的增量数据
    @PostMapping("/sync")
    public ResponseEntity<Void> uploadIncrementalData(
            @RequestParam Long userId,
            @RequestBody List<TodoItem> incrementalData) {
        todoService.saveIncrementalData(userId, incrementalData);
        return ResponseEntity.ok().build();
    }
}
