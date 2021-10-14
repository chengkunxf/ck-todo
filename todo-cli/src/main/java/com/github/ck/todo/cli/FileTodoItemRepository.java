package com.github.ck.todo.cli;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.github.ck.todo.core.TodoItem;
import com.github.ck.todo.core.TodoItemRepository;
import com.google.common.collect.ImmutableList;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/13 4:04 下午
 * @description
 */
public class FileTodoItemRepository implements TodoItemRepository {

    private final File file;
    private TypeFactory typeFactory = TypeFactory.defaultInstance();
    private ObjectMapper mapper = new ObjectMapper();

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
            return ImmutableList.of();
        }

        return fileToObjects();
    }

    private List<TodoItem> fileToObjects() {
        try {
            final CollectionType type = typeFactory.constructCollectionType(List.class, TodoItem.class);
            return mapper.readValue(file, type);
        } catch (IOException e) {
            throw new TodoException("Fail to read todo items", e);
        }
    }
}
