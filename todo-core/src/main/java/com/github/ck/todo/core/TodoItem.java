package com.github.ck.todo.core;

import lombok.Getter;

/**
 * @author chengkunxf@126.com
 * @date 2021/9/30 4:32 下午
 * @description
 */
@Getter
public class TodoItem {

    private final String content;

    public TodoItem(final String content) {
        this.content = content;
    }
}
