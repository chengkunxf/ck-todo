package com.github.ck.todo.core;

/**
 * @author chengkunxf@126.com
 * @date 2021/9/30 4:31 下午
 * @description
 */
public interface TodoItemRepository {

    TodoItem save(TodoItem todoItem);
}
