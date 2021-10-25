package com.github.ck.todo.cli.repository;

import com.github.ck.todo.cli.util.Jsons;
import com.github.ck.todo.service.TodoItem;
import com.github.ck.todo.service.TodoItemRepository;

import java.io.File;
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

    public FileTodoItemRepository(final File file) {
        this.file = file;
    }

    @Override
    public TodoItem save(final TodoItem todoItem) {
        List<TodoItem> all = this.findAll();
        if (todoItem.getIndex() == 0) {
            todoItem.assignIndex(all.size() + 1);
            all.add(todoItem);
            Jsons.writeToFile(file, all);
        } else {
            List<TodoItem> collect = all.stream()
                    .map(element -> update(element, todoItem))
                    .collect(Collectors.toList());
            Jsons.writeToFile(file, collect);
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

        return Jsons.readFromFile(file);
    }

}
