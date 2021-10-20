package com.github.ck.todo.cli.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/20 4:31 下午
 * @description
 */
class TodoExceptionTest {

    @Test
    public void should_create_todo_exception(){
        TodoException todoException = new TodoException("foo", new IllegalAccessException());
        assertThat(todoException).hasMessage("foo");
    }

}