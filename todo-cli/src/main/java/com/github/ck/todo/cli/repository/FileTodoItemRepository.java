package com.github.ck.todo.cli.repository;

import com.github.ck.todo.cli.util.Jsons;
import com.github.ck.todo.core.TodoItem;
import com.github.ck.todo.core.TodoItemRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<TodoItem> all = this.findAll();
        if (todoItem.getIndex() == 0) {
            todoItem.assignIndex(all.size() + 1);
            all.add(todoItem);
            Jsons.writeToFile(file, all);
        } else {
            List<TodoItem> collect = all.stream().map(element -> updateTodoItem(element, todoItem))
                    .collect(Collectors.toList());

            Jsons.writeToFile(file, collect);
        }
        return todoItem;
    }

    private TodoItem updateTodoItem(final TodoItem oldItem, final TodoItem newItem) {
        if (oldItem.getIndex() == newItem.getIndex()) {
            return newItem;
        }
        return oldItem;
    }

    @Override
    public List<TodoItem> findAll() {
        if (this.file.length() == 0) {
            return new ArrayList<>();
        }

        return Jsons.readFromFile(file);
    }

}
