package com.github.ck.todo.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @author chengkunxf@126.com
 * @date 2021/11/10 5:33 下午
 * @description
 */
public class AddTodoItemRequest {
    @Getter
    private String content;

    @JsonCreator
    public AddTodoItemRequest(@JsonProperty("content") final String content) {
        this.content = content;
    }
}

