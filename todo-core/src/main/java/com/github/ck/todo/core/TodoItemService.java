package com.github.ck.todo.core;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/22 2:42 下午
 * @description
 */
public class TodoItemService {

    private TodoItemRepository repository;

    public TodoItemService(final TodoItemRepository repository) {
        this.repository = repository;
    }

    public TodoItem addTodoItem(final TodoParameter todoParameter) {
        return null;
    }
}
