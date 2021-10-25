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

/**
 * @author chengkunxf@126.com
 * @date 2021/10/25 3:16 下午
 * @description
 */
public class FileTodoItemRepository implements TodoItemRepository {

    private File file;

    public FileTodoItemRepository(final File file) {
        this.file = file;
    }

    @Override
    public TodoItem save(final TodoItem todoItem) {
        return null;
    }

    @Override
    public List<TodoItem> findAll() {
        if (file.length() == 0) {
            return new ArrayList<>();
        }

        TypeFactory typeFactory = TypeFactory.defaultInstance();
        ObjectMapper mapper = new ObjectMapper();
        CollectionType collectionType = typeFactory.constructCollectionType(List.class, TodoItem.class);
        try {
            return mapper.readValue(file, collectionType);
        } catch (IOException e) {
            throw new TodoItemException("Fail to read data from file", e);
        }
    }
}
