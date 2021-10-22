package com.github.ck.todo.core;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<TodoItem> markTodoItemDone(final TodoIndexParameter indexParameter) {
        List<TodoItem> all = this.repository.findAll();
        Optional<TodoItem> optionalTodoItem = all.stream()
                .filter(element -> element.getIndex() == indexParameter.getIndex())
                .findFirst();
        return optionalTodoItem.flatMap(this::doAsdone);
    }

    private Optional<TodoItem> doAsdone(final TodoItem todoItem) {
        todoItem.markDone();
        return Optional.of(this.repository.save(todoItem));
    }

    public List<TodoItem> list(final boolean isAll) {
        List<TodoItem> all = this.repository.findAll();
        return all.stream().filter(element -> isAll || !element.isDone()).collect(Collectors.toList());
    }
}
