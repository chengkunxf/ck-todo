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
 * @date 2021/10/20 4:08 下午
 * @description
 */
public class FileTodoItemRepository implements TodoItemRepository {

    private File file;
    private TypeFactory typeFactory = TypeFactory.defaultInstance();
    private ObjectMapper mapper = new ObjectMapper();

    public FileTodoItemRepository(final File file) {
        this.file = file;
    }

    @Override
    public TodoItem save(final TodoItem todoItem) {
        List<TodoItem> all = findAll();
        if (todoItem.getIndex() == 0) {
            todoItem.assignIndex(all.size() + 1);
            all.add(todoItem);

            try {
                mapper.writeValue(file, all);
            } catch (IOException e) {
                throw new TodoException("Fail to write todoItem objects", e);
            }
        }
        return null;
    }

    @Override
    public List<TodoItem> findAll() {
        if (this.file.length() == 0) {
            return new ArrayList<TodoItem>();
        }

        CollectionType type = typeFactory.constructCollectionType(List.class, TodoItem.class);
        try {
            return mapper.readValue(file, type);
        } catch (IOException e) {
            throw new TodoException("Fail to read TodoItem from file", e);
        }
    }
}
