package com.github.ck.todo.core;

import lombok.Getter;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/11 4:26 下午
 * @description
 */
@Getter
public class TodoItem {

    private final String content;
    private int index;
    private boolean done;

    public TodoItem(final String content) {
        this.content = content;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void assignIndex(final int index) {
        this.index = index;
    }
}
