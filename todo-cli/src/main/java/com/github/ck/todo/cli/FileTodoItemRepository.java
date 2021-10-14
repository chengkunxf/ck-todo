package com.github.ck.todo.cli;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.github.ck.todo.cli.util.Jsons;
import com.github.ck.todo.core.TodoItem;
import com.github.ck.todo.core.TodoItemRepository;
import com.google.common.collect.ImmutableList;

import java.io.File;
import java.util.List;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/13 4:04 下午
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
            return ImmutableList.of();
        }

        return Jsons.fileToObjects(file);
    }

}
