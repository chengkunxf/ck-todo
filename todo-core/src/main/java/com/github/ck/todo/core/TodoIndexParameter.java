package com.github.ck.todo.core;

import lombok.Getter;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/22 3:08 下午
 * @description
 */
@Getter
public class TodoIndexParameter {
    private final int index;

    public TodoIndexParameter(final int index) {
        this.index = index;
    }
}
