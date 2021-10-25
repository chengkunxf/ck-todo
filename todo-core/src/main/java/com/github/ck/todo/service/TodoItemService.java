package com.github.ck.todo.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<TodoItem> markTodoItemDone(final TodoIndexParameter todoIndexParameter) {
        List<TodoItem> all = this.repository.findAll();
        Optional<TodoItem> todoItemOptional = all.stream()
                .filter(element -> element.getIndex() == todoIndexParameter.getIndex())
                .findFirst();

        return todoItemOptional.flatMap(this::doAsDone);
    }

    private Optional<TodoItem> doAsDone(final TodoItem todoItem) {
        todoItem.markDone();
        return Optional.of(this.repository.save(todoItem));
    }

    public List<TodoItem> list(final boolean isAll) {
        List<TodoItem> all = this.repository.findAll();
        return all.stream()
                .filter(element -> isAll || !element.isDone())
                .collect(Collectors.toList());
    }
}
