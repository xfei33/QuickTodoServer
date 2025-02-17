package com.quicktodo.server.controller;

import com.quicktodo.server.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utils")
public class UtilsController {
    @Autowired
    private TodoService todoService;

//    @GetMapping("/latestTime")
//    public ResponseEntity<LocalDateTime> getLatestTime(
//            @RequestParam Long userId
//    ) {
//        return ResponseEntity.ok(todoService.getLatestTodoItem(userId));
//    }

}
