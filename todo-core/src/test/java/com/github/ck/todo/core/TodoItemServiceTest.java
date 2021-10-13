package com.github.ck.todo.core;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/13 11:53 上午
 * @description
 */
public class TodoItemServiceTest {

    private TodoItemRepository repository;
    private TodoItemService service;

    @BeforeEach
    private void setUp() {
        repository = mock(TodoItemRepository.class);
        service = new TodoItemService(repository);
    }

    @Test
    public void should_add_todo_item() {
        when(repository.save(any())).then(returnsFirstArg());
        TodoItem todoItem = service.addTodoItem(new TodoParameter("foo"));
        assertThat(todoItem.getContent()).isEqualTo("foo");
    }

    @Test
    public void should_mark_todo_item_done() {
        TodoItem todoItem = new TodoItem("foo");
        todoItem.assignIndex(1);
        when(repository.findAll()).thenReturn(ImmutableList.of(todoItem));
        when(repository.save(any())).then(returnsFirstArg());

        Optional<TodoItem> optionalTodoItem = service.markTodoItemDone(new TodoIndexParameter(1));
        assertThat(optionalTodoItem).isPresent();
        TodoItem todoItem1 = optionalTodoItem.get();
        assertThat(todoItem1.isDone()).isTrue();

    }

    @Test
    public void should_mark_todo_item_done_out_of_index(){
        TodoItem todoItem = new TodoItem("foo");
        todoItem.assignIndex(1);
        when(repository.findAll()).thenReturn(ImmutableList.of(todoItem));
        when(repository.save(any())).then(returnsFirstArg());

        Optional<TodoItem> optionalTodoItem = service.markTodoItemDone(new TodoIndexParameter(2));
        assertThat(optionalTodoItem).isEmpty();
    }
}
