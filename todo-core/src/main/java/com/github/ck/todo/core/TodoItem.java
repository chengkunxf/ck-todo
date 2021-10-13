package com.github.ck.todo.core;

import lombok.Getter;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/13 11:57 上午
 * @description
 */
@Getter
public class TodoItem {

    private final String content;

    public TodoItem(final String content) {
        this.content = content;
    }
}
