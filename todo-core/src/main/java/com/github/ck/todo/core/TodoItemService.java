package com.github.ck.todo.core;

import org.springframework.stereotype.Service;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/12 10:57 上午
 * @description
 */
@Service
public class TodoItemService {

    private final TodoItemRepository repository;

    public TodoItemService(final TodoItemRepository repository) {
        this.repository = repository;
    }

    public TodoItem addTodoItem(final TodoParameter parameter) {
        if (parameter == null) {
            throw new IllegalArgumentException("Null or empty is not allowed");
        }
        TodoItem todoItem = new TodoItem(parameter.getContent());
        return this.repository.save(todoItem);
    }
}
