package com.github.ck.todo.core;

import lombok.Getter;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/12 10:58 上午
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

    public void assignIndex(final int index) {
        this.index = index;
    }

    public void markDone() {
        this.done = true;
    }
}
