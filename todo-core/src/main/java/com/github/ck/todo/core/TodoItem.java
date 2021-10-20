package com.github.ck.todo.core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/13 11:57 上午
 * @description
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TodoItem {

    private String content;
    private int index;
    private boolean done;

    public TodoItem(final String content) {
        this.content = content;
    }

    public void assignIndex(final int index) {
        this.index = index;
    }

    public void markDone() {
        this.done = true;
    }
}
