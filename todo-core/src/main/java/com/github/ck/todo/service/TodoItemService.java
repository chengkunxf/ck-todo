package com.github.ck.todo.service;

import org.springframework.stereotype.Service;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/22 6:02 下午
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
        return repository.save(todoItem);
    }
}
