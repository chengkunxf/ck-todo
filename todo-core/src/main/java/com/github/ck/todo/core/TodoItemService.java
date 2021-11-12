package com.github.ck.todo.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/12 10:57 上午
 * @description
 */
@Service
public class TodoItemService {

    private final TodoItemRepository repository;

    @Autowired
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

    public Optional<TodoItem> markTodoItemDone(final TodoIndexParameter parameter) {
        List<TodoItem> all = this.repository.findAll();
        Optional<TodoItem> optionalTodoItem = all.stream().
                filter(element -> element.getIndex() == parameter.getIndex())
                .findFirst();
        return optionalTodoItem.flatMap(this::doAsDone);
    }

    private Optional<TodoItem> doAsDone(final TodoItem todoItem) {
        todoItem.markDone();
        return Optional.of(repository.save(todoItem));
    }

    public List<TodoItem> list(final boolean isAll) {
        List<TodoItem> all = repository.findAll();
        return all.stream()
                .filter(element -> isAll || !element.isDone())
                .collect(Collectors.toList());
    }
}
