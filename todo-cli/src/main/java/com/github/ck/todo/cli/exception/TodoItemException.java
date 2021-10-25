package com.github.ck.todo.cli.exception;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/25 3:24 下午
 * @description
 */
public class TodoItemException extends RuntimeException {

    public TodoItemException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
