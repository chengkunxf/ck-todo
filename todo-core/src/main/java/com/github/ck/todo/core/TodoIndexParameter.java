package com.github.ck.todo.core;

import lombok.Getter;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/12 11:32 上午
 * @description
 */
@Getter
public class TodoIndexParameter {

    private final int index;

    public TodoIndexParameter(final int index) {
        this.index = index;
    }
}
