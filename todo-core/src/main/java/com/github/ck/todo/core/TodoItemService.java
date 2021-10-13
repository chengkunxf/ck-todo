package com.github.ck.todo.core;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/13 11:56 上午
 * @description
 */
public class TodoItemService {

    private final TodoItemRepository repository;

    public TodoItemService(final TodoItemRepository repository) {
        this.repository = repository;
    }

    public TodoItem addTodoItem(final TodoParameter parameter) {
        TodoItem todoItem = new TodoItem(parameter.getContent());
        return repository.save(todoItem);
    }
}
