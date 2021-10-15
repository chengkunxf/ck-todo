package com.github.ck.todo.cli.repository;

import com.github.ck.todo.cli.util.Jsons;
import com.github.ck.todo.core.TodoItem;
import com.github.ck.todo.core.TodoItemRepository;

import java.io.File;
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

        return Jsons.readFromFile(file);
    }

}
