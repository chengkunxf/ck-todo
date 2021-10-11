package com.github.ck.todo.core;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/11 4:19 下午
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
    public void should_throw_exception_for_null_item() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> service.addTodoItem(null));
    }

    @Test
    public void should_mark_todo_item_as_done() {
        when(repository.save(any())).then(returnsFirstArg());
        TodoItem foo = new TodoItem("foo");
        foo.assignIndex(1);
        when(repository.findAll()).thenReturn(ImmutableList.of(foo));
        Optional<TodoItem> optionalTodoItem = service.markTodoItemDone(new TodoIndexParameter(1));
        assertThat(optionalTodoItem.get().isDone()).isTrue();
    }

    @Test
    public void should_not_mark_todo_item_as_done_for_out_of_index() {
        when(repository.save(any())).then(returnsFirstArg());
        TodoItem foo = new TodoItem("foo");
        foo.assignIndex(1);
        when(repository.findAll()).thenReturn(ImmutableList.of(foo));
        Optional<TodoItem> optionalTodoItem = service.markTodoItemDone(new TodoIndexParameter(2));
        assertThat(optionalTodoItem).isEmpty();
    }

    @Test
    public void should_list_all() {
        TodoItem foo = new TodoItem("foo");
        foo.assignIndex(1);
        foo.markDone();
        TodoItem bar = new TodoItem("bar");
        bar.assignIndex(2);
        when(repository.findAll()).thenReturn(ImmutableList.of(foo, bar));

        List<TodoItem> list = service.list(true);
        assertThat(list).hasSize(2);
        TodoItem todoItem = list.get(0);
        assertThat(todoItem.getContent()).isEqualTo("foo");
        assertThat(todoItem.getIndex()).isEqualTo(1);
    }

    @Test
    public void should_list_all_not_done() {
        TodoItem foo = new TodoItem("foo");
        foo.assignIndex(1);
        foo.markDone();
        TodoItem bar = new TodoItem("bar");
        bar.assignIndex(2);
        when(repository.findAll()).thenReturn(ImmutableList.of(foo, bar));

        List<TodoItem> list = service.list(false);
        assertThat(list).hasSize(1);

        TodoItem todoItem = list.get(0);
        assertThat(todoItem.getIndex()).isEqualTo(2);
        assertThat(todoItem.getContent()).isEqualTo("bar");

    }
}
