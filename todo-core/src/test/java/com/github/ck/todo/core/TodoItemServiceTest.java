package com.github.ck.todo.core;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/22 2:39 下午
 * @description
 */
public class TodoItemServiceTest {

    private TodoItemRepository repository;
    private TodoItemService service;

    @BeforeEach
    void setUp() {
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
    public void should_throw_exception_for_todo_parameter_null() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> service.addTodoItem(null));
    }

    @Test
    public void should_mark_todo_item_as_done() {
        TodoItem foo = new TodoItem("foo");
        foo.assignIndex(1);
        when(repository.findAll()).thenReturn(ImmutableList.of(foo));
        when(repository.save(any())).then(returnsFirstArg());

        Optional<TodoItem> optionalTodoItem = service.markTodoItemDone(new TodoIndexParameter(1));

        assertThat(optionalTodoItem).isPresent();
        TodoItem todoItem = optionalTodoItem.get();
        assertThat(todoItem.isDone()).isTrue();
    }

    @Test
    public void should_mark_todo_item_out_of_index() {
        TodoItem foo = new TodoItem("foo");
        foo.assignIndex(1);
        when(repository.findAll()).thenReturn(ImmutableList.of(foo));
        when(repository.save(any())).then(returnsFirstArg());

        Optional<TodoItem> optionalTodoItem = service.markTodoItemDone(new TodoIndexParameter(2));

        assertThat(optionalTodoItem).isEmpty();
    }
}
