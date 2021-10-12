package com.github.ck.todo.core;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/12 10:57 上午
 * @description
 */
public interface TodoItemRepository {

    TodoItem save(TodoItem todoItem);
}
