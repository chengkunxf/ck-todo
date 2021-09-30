package com.github.ck.todo.core;

import lombok.Getter;

/**
 * @author chengkunxf@126.com
 * @date 2021/9/30 4:47 下午
 * @description
 */
@Getter
public class TodoParameter {

    private final String content;

    public TodoParameter(final String content) {
        this.content = content;
    }
}
