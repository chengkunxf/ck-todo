package com.github.ck.todo.core;

import org.junit.jupiter.api.Test;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/13 11:53 上午
 * @description
 */
public class TodoItemServiceTest {

    @Test
    public void should_add_todo_item() {
        TodoRepository repository = null;
        TodoItemService service = new TodoItemService(repository);
        TodoItem todoItem = service.addTodoItem(new TodoParameter("foo"));
        assertThat(todoItem.getContent()).isEqualTo("foo");
    }
}
