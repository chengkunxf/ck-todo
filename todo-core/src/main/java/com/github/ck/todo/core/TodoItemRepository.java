package com.github.ck.todo.core;

import java.util.List;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/13 11:56 上午
 * @description
 */
public interface TodoItemRepository {
    TodoItem save(TodoItem todoItem);

    List<TodoItem> findAll();
}
