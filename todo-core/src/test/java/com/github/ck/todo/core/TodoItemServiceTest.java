package com.github.ck.todo.core;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    public void should_add_todo_item() {
        TodoItemRepository repository = mock(TodoItemRepository.class);
        when(repository.save(any())).then(returnsFirstArg());
        TodoItemService service = new TodoItemService(repository);
        TodoItem todoItem = service.addTodoItem(new TodoParameter("foo"));
        assertThat(todoItem.getContent()).isEqualTo("foo");
    }

    @Test
    public void should_mark_todo_item_as_done(){
        TodoItemRepository repository = mock(TodoItemRepository.class);
        when(repository.save(any())).then(returnsFirstArg());
        TodoItem foo = new TodoItem("foo");
        foo.assignIndex(1);
        when(repository.findAll()).thenReturn(ImmutableList.of(foo));
        TodoItemService service = new TodoItemService(repository);
        TodoItem todoItem = service.markTodoItemDone(new TodoIndexParameter(1));
        assertThat(todoItem.isDone()).isTrue();
    }

    @Test
    public void should_not_mark_todo_item_as_done_for_out_of_index(){
        TodoItemRepository repository = mock(TodoItemRepository.class);
        when(repository.save(any())).then(returnsFirstArg());
        TodoItem foo = new TodoItem("foo");
        foo.assignIndex(1);
        when(repository.findAll()).thenReturn(ImmutableList.of(foo));
        TodoItemService service = new TodoItemService(repository);
        TodoItem todoItem = service.markTodoItemDone(new TodoIndexParameter(2));
        assertThat(todoItem).isNull();
    }
}
