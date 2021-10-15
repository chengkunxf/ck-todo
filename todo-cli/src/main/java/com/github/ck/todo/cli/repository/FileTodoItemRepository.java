package com.github.ck.todo.cli.repository;

import com.github.ck.todo.core.TodoItem;
import com.github.ck.todo.core.TodoItemRepository;
import com.github.ck.todo.util.Jsons;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<TodoItem> all = this.findAll();

        if (todoItem.getIndex() == 0) {
            todoItem.assignIndex(all.size() + 1);
            all.add(todoItem);
            Jsons.objectsToFile(this.file, all);
        } else {
            List<TodoItem> newAll = all.stream()
                    .map(element -> updateItem(todoItem, element))
                    .collect(Collectors.toList());
            Jsons.objectsToFile(this.file, newAll);
        }

        return todoItem;
    }

    private TodoItem updateItem(final TodoItem todoItem, final TodoItem element) {
        if (element.getIndex() == todoItem.getIndex()) {
            return todoItem;
        }
        return element;
    }

    @Override
    public List<TodoItem> findAll() {
        if (this.file.length() == 0) {
            return new ArrayList<>();
        }

        return Jsons.fileToObjects(file);
    }

}
