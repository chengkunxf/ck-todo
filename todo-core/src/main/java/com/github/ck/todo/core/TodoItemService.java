package com.github.ck.todo.core;

import org.springframework.stereotype.Service;

/**
 * @author chengkunxf@126.com
 * @date 2021/9/30 4:45 下午
 * @description
 */
@Service
public class TodoItemService {

    private final TodoItemRepository repository;

    public TodoItemService(final TodoItemRepository repository) {
        this.repository = repository;
    }

    public TodoItem addTodoItem(final TodoParameter todoParameter) {
        TodoItem todoItem = new TodoItem(todoParameter.getContent());
        return this.repository.save(todoItem);
    }
}
