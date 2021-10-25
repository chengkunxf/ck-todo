package com.github.ck.todo.service;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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
        TodoItem todoItem = service.addTodoItem(new TodoParameter("itemfoo"));
        assertThat(todoItem.getContent()).isEqualTo("itemfoo");
    }

    @Test
    public void should_mark_todo_item_done() {

        TodoItem foo = new TodoItem("foo");
        foo.assignIndex(1);
        when(repository.save(any())).then(returnsFirstArg());
        when(repository.findAll()).thenReturn(ImmutableList.of(foo));

        Optional<TodoItem> todoItemOptional = service.markTodoItemDone(new TodoIndexParameter(1));
        TodoItem todoItem = todoItemOptional.get();
        assertThat(todoItem.isDone()).isTrue();
    }

    @Test
    public void should_mark_todo_item_done_out_index() {
        TodoItem foo = new TodoItem("foo");
        foo.assignIndex(1);
        when(repository.save(any())).then(returnsFirstArg());
        when(repository.findAll()).thenReturn(ImmutableList.of(foo));

        Optional<TodoItem> todoItemOptional = service.markTodoItemDone(new TodoIndexParameter(2));
        assertThat(todoItemOptional).isEmpty();

    }

    @Test
    public void should_list() {
        TodoItem foo = new TodoItem("foo");
        foo.assignIndex(1);
        when(repository.save(any())).then(returnsFirstArg());
        when(repository.findAll()).thenReturn(ImmutableList.of(foo));

        List<TodoItem> all = service.list(true);
        assertThat(all).hasSize(1);
    }

    @Test
    public void should_list_all_include_done_and_not_done() {
        TodoItem foo = new TodoItem("foo");
        foo.assignIndex(1);
        foo.markDone();
        TodoItem bar = new TodoItem("bar");
        bar.assignIndex(2);


        when(repository.save(any())).then(returnsFirstArg());
        when(repository.findAll()).thenReturn(ImmutableList.of(foo, bar));

        List<TodoItem> list = service.list(false);
        assertThat(list).hasSize(1);
        TodoItem bar2 = list.get(0);
        assertThat(bar2.isDone()).isFalse();

    }
}
