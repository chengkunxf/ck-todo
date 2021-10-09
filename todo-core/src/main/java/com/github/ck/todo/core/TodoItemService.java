package com.github.ck.todo.core;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if (todoParameter == null) {
            throw new IllegalArgumentException("Null or empty content is not allowed");
        }
        TodoItem todoItem = new TodoItem(todoParameter.getContent());
        return this.repository.save(todoItem);
    }

    public Optional<TodoItem> markTodoItemDone(final TodoIndexParameter todoIndexParameter) {
        List<TodoItem> all = this.repository.findAll();
        Optional<TodoItem> optionalTodoItem = all.stream()
                .filter(element -> element.getIndex() == todoIndexParameter.getIndex())
                .findFirst();
//        if (optionalTodoItem.isPresent()) {
//            TodoItem todoItem = optionalTodoItem.get();
//            todoItem.markDone();
//            todoItem = this.repository.save(todoItem);
//            return Optional.of(todoItem);
//        }
//        return Optional.empty();
        return optionalTodoItem.flatMap(this::doMarkAsDone);
    }

    private Optional<TodoItem> doMarkAsDone(final TodoItem todoItem) {
        todoItem.markDone();
        return Optional.of(this.repository.save(todoItem));
    }

}
