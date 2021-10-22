package com.github.ck.todo.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public TodoItem markTodoItemDone(final TodoIndexParameter todoIndexParameter) {
        List<TodoItem> all = this.repository.findAll();
        Optional<TodoItem> todoItemOptional = all.stream()
                .filter(element -> element.getIndex() == todoIndexParameter.getIndex())
                .findFirst();

        if (todoItemOptional.isPresent()) {
            TodoItem todoItem = todoItemOptional.get();
            todoItem.markDone();
            return this.repository.save(todoItem);
        }
        return null;
    }
}
