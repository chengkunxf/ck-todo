package com.github.ck.todo.core;

import java.util.List;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/11 4:24 下午
 * @description
 */
public interface TodoItemRepository {

    TodoItem save(TodoItem todoItem);

    List<TodoItem> findAll();

}
