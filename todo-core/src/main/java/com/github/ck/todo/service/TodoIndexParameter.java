package com.github.ck.todo.service;

import lombok.Getter;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/22 6:19 下午
 * @description
 */

@Getter
public class TodoIndexParameter {

    private final int index;

    public TodoIndexParameter(final int index) {
        this.index = index;
    }
}
