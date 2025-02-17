package com.quicktodo.server.service;

import com.quicktodo.server.model.TodoItem;
import com.quicktodo.server.model.TodoRequest;
import com.quicktodo.server.repository.TodoRepository;
import com.quicktodo.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    // 获取服务端的增量数据
    public List<TodoRequest> getIncrementalData(Long userId, LocalDateTime lastSyncTime) {
        List<TodoItem> todoItems = todoRepository.findByUserIdAndLastModifiedAfter(userId, lastSyncTime);
        List<TodoRequest> incrementalData = new ArrayList<>();
        todoItems.forEach(todoItem -> {
            TodoRequest todoRequest = new TodoRequest();
            todoRequest.setId(todoItem.getId());
            todoRequest.setTitle(todoItem.getTitle());
            todoRequest.setDescription(todoItem.getDescription());
            todoRequest.setDueDate(todoItem.getDueDate());
            todoRequest.setUserId(todoItem.getUser().getId());
            todoRequest.setPriority(todoItem.getPriority());
            todoRequest.setTag(todoItem.getTag());
            todoRequest.setCompleted(todoItem.isCompleted());
            todoRequest.setLastModified(todoItem.getLastModified());
            todoRequest.setDeleted(todoItem.isDeleted());
            incrementalData.add(todoRequest);
        });
        return incrementalData;
    }

    // 保存客户端的增量数据
    public void saveIncrementalData(Long userId, List<TodoRequest> incrementalData) {
        for (TodoRequest todo : incrementalData) {
            TodoItem todoItem = new TodoItem();
            todoItem.setId(todo.getId());
            todoItem.setTitle(todo.getTitle());
            todoItem.setDescription(todo.getDescription());
            todoItem.setDueDate(todo.getDueDate());
            todoItem.setPriority(todo.getPriority());
            todoItem.setTag(todo.getTag());
            todoItem.setCompleted(todo.isCompleted());
            todoItem.setLastModified(todo.getLastModified());
            todoItem.setDeleted(todo.isDeleted());
            todoItem.setUser(userRepository.findUserById(userId).orElse(null));
            todoRepository.save(todoItem);
        }
    }

//    // 获取最新 TodoItem 的最后修改时间
//    public LocalDateTime getLatestTodoItem(Long userId) {
//        TodoItem latestTodoItem = todoRepository.findFirstByUserIdOrderByLastModifiedDesc(userId);
//        if (latestTodoItem == null) {
//            return LocalDateTime.now();
//        }
//        return latestTodoItem.getLastModified();
//    }
}

