package com.github.ck.todo.service;

import org.junit.jupiter.api.Test;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/22 5:58 下午
 * @description
 */
public class TodoItemServiceTest {

    @Test
    public void should_add_todo_item() {
        TodoItemRepository repository = null;
        TodoItemService service = new TodoItemService(repository);
        TodoItem todoItem = service.addTodoItem(new TodoParameter("itemfoo"));
        assertThat(todoItem.getContent()).isEqualTo("itemfoo");
    }
}
