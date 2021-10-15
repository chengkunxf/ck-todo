package com.github.ck.todo.cli.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
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
 * @date 2021/10/15 10:26 上午
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
        if (this.file.length() == 0) {
            return new ArrayList<>();
        }

        TypeFactory typeFactory = TypeFactory.defaultInstance();
        ObjectMapper mapper = new ObjectMapper();

        try {
            CollectionLikeType type = typeFactory.constructCollectionType(List.class, TodoItem.class);
            return mapper.readValue(file, type);
        } catch (IOException e) {
            throw new TodoException("fail to read todoItem", e);
        }
    }
}
