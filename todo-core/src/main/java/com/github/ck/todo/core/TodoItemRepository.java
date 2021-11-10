package com.github.ck.todo.core;

import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/12 10:57 上午
 * @description
 */
public interface TodoItemRepository extends Repository<TodoItem, Long> {

    TodoItem save(TodoItem todoItem);

    List<TodoItem> findAll();
}
