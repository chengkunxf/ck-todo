package com.github.ck.todo.core;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/22 2:41 下午
 * @description
 */
public interface TodoItemRepository {
    TodoItem save(TodoItem todoItem);
}
