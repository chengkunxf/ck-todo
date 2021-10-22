package com.github.ck.todo.service;

import lombok.Getter;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/22 6:02 下午
 * @description
 */
@Getter
public class TodoItem {
    private String content;

    public TodoItem(final String content) {
        this.content = content;
    }

}
