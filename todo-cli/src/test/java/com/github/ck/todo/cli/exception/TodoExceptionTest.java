package com.github.ck.todo.cli.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/15 10:44 上午
 * @description
 */
class TodoExceptionTest {

    @Test
    public void should_create_todo_exception(){
        TodoException todoException = new TodoException("foo", new IllegalArgumentException());
        assertThat(todoException).hasMessage("foo");
    }

}