package com.github.ck.todo.core;

import lombok.Getter;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/13 11:58 上午
 * @description
 */
@Getter
public class TodoParameter {
    private final String content;

    public TodoParameter(final String content) {
        this.content = content;
    }
}
