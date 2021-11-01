package com.github.ck.todo.api.repository;

import com.github.ck.todo.service.TodoItem;
import com.github.ck.todo.service.TodoItemRepository;
import com.google.common.collect.Iterables;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/29 11:43 上午
 * @description
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:test.properties")
public class TodoItemRepositoryTest {

    @Autowired
    private TodoItemRepository repository;

    @Test
    public void should_find_by_nothing_repository() {
        List<TodoItem> all = this.repository.findAll();
        assertThat(all).hasSize(0);
    }

    @Test
    public void should_save_todo_item() {
        TodoItem foo = new TodoItem("foo");
        TodoItem bar = new TodoItem("bar");
        this.repository.save(foo);
        this.repository.save(bar);

        List<TodoItem> all = this.repository.findAll();
        assertThat(all).hasSize(2);
    }

    @Test
    public void should_update_todo_item() {
        TodoItem foo = new TodoItem("foo");
        TodoItem bar = new TodoItem("bar");
        this.repository.save(foo);
        this.repository.save(bar);

        final Iterable<TodoItem> items = repository.findAll();
        final TodoItem toUpdate = Iterables.get(items, 0);
        toUpdate.markDone();

        repository.save(toUpdate);

        final Iterable<TodoItem> updated = repository.findAll();
        assertThat(updated).hasSize(2);
        assertThat(Iterables.get(items, 0).isDone()).isTrue();
    }
}
