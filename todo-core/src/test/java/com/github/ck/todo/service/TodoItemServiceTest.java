package com.github.ck.todo.service;

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
    public void should_mark_todo_item_done_out_index(){
        TodoItem foo = new TodoItem("foo");
        foo.assignIndex(1);
        when(repository.save(any())).then(returnsFirstArg());
        when(repository.findAll()).thenReturn(ImmutableList.of(foo));

        Optional<TodoItem> todoItemOptional = service.markTodoItemDone(new TodoIndexParameter(2));
        assertThat(todoItemOptional).isEmpty();

    }
}
