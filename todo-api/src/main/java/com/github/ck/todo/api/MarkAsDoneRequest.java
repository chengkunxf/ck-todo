package com.github.ck.todo.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @author chengkunxf@126.com
 * @date 2021/11/15 10:11 上午
 * @description
 */
public class MarkAsDoneRequest {
    @Getter
    private boolean done;

    @JsonCreator
    public MarkAsDoneRequest(@JsonProperty("done") final boolean done) {
        this.done = done;
    }
}
