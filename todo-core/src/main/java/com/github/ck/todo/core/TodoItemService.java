package com.github.ck.todo.core;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public TodoItem markTodoItemDone(final TodoIndexParameter parameter) {
        List<TodoItem> all = this.repository.findAll();
        Optional<TodoItem> optionalTodoItem = all.stream().
                filter(element -> element.getIndex() == parameter.getIndex())
                .findFirst();
        if (optionalTodoItem.isPresent()) {
            TodoItem todoItem = optionalTodoItem.get();
            todoItem.markDone();
            return this.repository.save(todoItem);
        }
        return null;
    }
}
