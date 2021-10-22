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
    private boolean done;
    private int index;


    public TodoItem(final String content) {
        this.content = content;
    }

    public void markDone() {
        this.done = true;
    }

    public void assignIndex(final int index) {
        this.index = index;
    }
}
