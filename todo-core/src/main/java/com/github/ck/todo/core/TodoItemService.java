package com.github.ck.todo.core;

import java.util.List;
import java.util.Optional;

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

    public Optional<TodoItem> markTodoItemDone(final TodoIndexParameter todoIndexParameter) {
        List<TodoItem> all = this.repository.findAll();
        Optional<TodoItem> optionalTodoItem = all.stream()
                .filter(element -> element.getIndex() == todoIndexParameter.getIndex())
                .findFirst();

        return optionalTodoItem.flatMap(this::doAsDone);
    }

    private Optional<TodoItem> doAsDone(final TodoItem todoItem) {
        todoItem.markDone();
        return Optional.of(this.repository.save(todoItem));
    }
}
