package com.github.ck.todo.cli.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.github.ck.todo.cli.exception.TodoException;
import com.github.ck.todo.core.TodoItem;
import com.github.ck.todo.core.TodoItemRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/15 2:40 下午
 * @description
 */
public class FileTodoItemRepository implements TodoItemRepository {
    private final File file;

    public FileTodoItemRepository(final File tempFile) {
        this.file = tempFile;
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
            throw new TodoException("fail to read todo item", e);
        }
    }
}
