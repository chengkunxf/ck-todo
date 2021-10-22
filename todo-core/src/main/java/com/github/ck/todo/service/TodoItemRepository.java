package com.github.ck.todo.service;

import java.util.List;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/22 6:02 下午
 * @description
 */
public interface TodoItemRepository {
    TodoItem save(TodoItem todoItem);

    List<TodoItem> findAll();
}
