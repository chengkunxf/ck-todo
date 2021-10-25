package com.github.ck.todo.cli.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/25 3:31 下午
 * @description
 */
class TodoItemExceptionTest {

    @Test
    public void should_create_todo_exception() {
        TodoItemException todoItemException = new TodoItemException("foo", new IllegalArgumentException());
        assertThat(todoItemException).hasMessage("foo");
    }


}