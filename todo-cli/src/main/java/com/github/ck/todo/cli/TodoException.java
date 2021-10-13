package com.github.ck.todo.cli;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/13 4:34 下午
 * @description
 */
public class TodoException extends RuntimeException {

    public TodoException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
