package com.github.ck.todo.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/22 5:58 下午
 * @description
 */
public class TodoItemServiceTest {

    @Test
    public void should_add_todo_item() {
        TodoItemRepository repository = mock(TodoItemRepository.class);
        when(repository.save(any())).then(returnsFirstArg());
        TodoItemService service = new TodoItemService(repository);
        TodoItem todoItem = service.addTodoItem(new TodoParameter("itemfoo"));
        assertThat(todoItem.getContent()).isEqualTo("itemfoo");
    }
}
