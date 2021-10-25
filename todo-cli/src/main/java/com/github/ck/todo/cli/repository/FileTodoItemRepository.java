package com.github.ck.todo.cli.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.github.ck.todo.cli.exception.TodoItemException;
import com.github.ck.todo.service.TodoItem;
import com.github.ck.todo.service.TodoItemRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/25 3:16 下午
 * @description
 */
public class FileTodoItemRepository implements TodoItemRepository {

    private File file;
    private final TypeFactory typeFactory = TypeFactory.defaultInstance();
    private final ObjectMapper mapper = new ObjectMapper();

    public FileTodoItemRepository(final File file) {
        this.file = file;
    }

    @Override
    public TodoItem save(final TodoItem todoItem) {
        List<TodoItem> all = this.findAll();
        if (todoItem.getIndex() == 0) {
            todoItem.assignIndex(all.size() + 1);
            all.add(todoItem);
            try {
                mapper.writeValue(file, all);
            } catch (IOException e) {
                throw new TodoItemException("Fail to write data to file", e);
            }
        } else {
            List<TodoItem> collect = all.stream()
                    .map(element -> update(element, todoItem))
                    .collect(Collectors.toList());
            try {
                mapper.writeValue(file, collect);
            } catch (IOException e) {
                throw new TodoItemException("Fail to write data to file", e);
            }
        }
        return todoItem;
    }

    private TodoItem update(final TodoItem oldElement, final TodoItem newElement) {
        if (oldElement.getIndex() == newElement.getIndex()) {
            return newElement;
        }
        return oldElement;
    }

    @Override
    public List<TodoItem> findAll() {
        if (file.length() == 0) {
            return new ArrayList<>();
        }

        CollectionType collectionType = typeFactory.constructCollectionType(List.class, TodoItem.class);
        try {
            return mapper.readValue(file, collectionType);
        } catch (IOException e) {
            throw new TodoItemException("Fail to read data from file", e);
        }
    }
}
