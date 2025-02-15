package com.quicktodo.server.service;

import com.quicktodo.server.model.TodoItem;
import com.quicktodo.server.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    // 获取服务端的增量数据
    public List<TodoItem> getIncrementalData(Long userId, LocalDateTime lastSyncTime) {
        return todoRepository.findByUserIdAndLastModifiedAfter(userId, lastSyncTime);
    }

    // 保存客户端的增量数据
    public void saveIncrementalData(Long userId, List<TodoItem> incrementalData) {
        for (TodoItem todoItem : incrementalData) {
            // 正确创建 User 对象
            todoItem.setUserId(userId); // 设置用户 ID
            todoItem.setLastModified(LocalDateTime.now()); // 更新最后修改时间
            todoRepository.save(todoItem);
        }
    }

    // 获取最新 TodoItem 的最后修改时间
    public LocalDateTime getLatestTodoItem(Long userId) {
        TodoItem latestTodoItem = todoRepository.findFirstByUserIdOrderByLastModifiedDesc(userId);
        if (latestTodoItem == null) {
            return LocalDateTime.now();
        }
        return latestTodoItem.getLastModified();
    }
}

