package com.github.ck.todo.cli;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/13 4:41 下午
 * @description
 */
class TodoExceptionTest {

    @Test
    public void should_create_todo_item_exception() {
        TodoException todoException = new TodoException("foo", new IllegalArgumentException());
        assertThat(todoException).hasMessage("foo");
    }

}