package com.github.ck.todo.cli.exception;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/15 10:39 上午
 * @description
 */
public class TodoException extends RuntimeException {
    public TodoException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
