package com.github.ck.todo.core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author chengkunxf@126.com
 * @date 2021/9/30 4:32 下午
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
        this.done = false;
    }

    public void assignIndex(final int index) {
        this.index = index;
    }

    public void markDone() {
        this.done = true;
    }
}
