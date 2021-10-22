package com.github.ck.todo.service;

import lombok.Getter;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/22 6:02 下午
 * @description
 */
@Getter
public class TodoParameter {
    private String content;

    public TodoParameter(final String content) {
        this.content = content;
    }

}
