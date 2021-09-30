package com.github.ck.todo.core;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chengkunxf@126.com
 * @date 2021/9/30 4:26 下午
 * @description
 */
public class TodoItemServiceTest {
    @Test
    public void should_add_todo_item() {
        TodoItemRepository repository = null;
        TodoItemService service = new TodoItemService(repository);
        TodoItem todoItem = service.addTodoItem(new TodoParameter("foo"));
        assertThat(todoItem.getContent()).isEqualTo("foo");
    }
}
