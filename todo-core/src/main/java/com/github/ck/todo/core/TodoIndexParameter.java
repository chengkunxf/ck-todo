package com.github.ck.todo.core;

import lombok.Getter;

/**
 * @author chengkunxf@126.com
 * @date 2021/9/30 5:07 下午
 * @description
 */
@Getter
public class TodoIndexParameter {

    private final int index;

    public TodoIndexParameter(final int index) {
        this.index = index;
    }
}
