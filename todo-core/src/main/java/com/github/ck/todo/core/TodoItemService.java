package com.github.ck.todo.core;

import org.springframework.stereotype.Service;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/22 2:42 下午
 * @description
 */
@Service
public class TodoItemService {

    private TodoItemRepository repository;

    public TodoItemService(final TodoItemRepository repository) {
        this.repository = repository;
    }

    public TodoItem addTodoItem(final TodoParameter todoParameter) {
        if (todoParameter == null) {
            throw new IllegalArgumentException("Null or empty is not allowed");
        }
        TodoItem todoItem = new TodoItem(todoParameter.getContent());
        return this.repository.save(todoItem);
    }
}
