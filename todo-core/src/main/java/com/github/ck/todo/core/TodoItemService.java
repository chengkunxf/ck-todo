package com.github.ck.todo.core;

import org.springframework.stereotype.Service;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/11 4:26 下午
 * @description
 */
@Service
public class TodoItemService {

    private final TodoItemRepository repository;

    public TodoItemService(final TodoItemRepository repository) {
        this.repository = repository;
    }

    public TodoItem addTodoItem(final TodoParameter parameter) {
        return this.repository.save(new TodoItem(parameter.getContent()));
    }
}
