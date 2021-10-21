package com.github.ck.todo.cli.repository;

import com.github.ck.todo.core.TodoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/20 4:05 下午
 * @description
 */
public class FileTodoItemRepositoryTest {

    @TempDir
    File tempDir;
    File tempFile;
    FileTodoItemRepository repository;

    @BeforeEach
    void setUp() throws IOException {
        this.tempFile = File.createTempFile("file", "", tempDir);
        repository = new FileTodoItemRepository(tempFile);
    }

    @Test
    public void find_nothing_from_empty_repository() {
        List<TodoItem> all = repository.findAll();
        assertThat(all).hasSize(0);
    }

    @Test
    public void save_todo_item(){
        this.repository.save(new TodoItem("foo"));
        this.repository.save(new TodoItem("bar"));

        List<TodoItem> all = this.repository.findAll();
        assertThat(all).hasSize(2);

        TodoItem foo = all.get(0);
        assertThat(foo.getIndex()).isEqualTo(1);
        assertThat(foo.getContent()).isEqualTo("foo");

        TodoItem bar = all.get(1);
        assertThat(bar.getIndex()).isEqualTo(2);
        assertThat(bar.getContent()).isEqualTo("bar");

    }

    @Test
    public void update_todo_item(){
        this.repository.save(new TodoItem("foo"));
        this.repository.save(new TodoItem("bar"));

        List<TodoItem> all = this.repository.findAll();
        assertThat(all).hasSize(2);

        TodoItem bar2 = new TodoItem("bar2");
        bar2.assignIndex(2);
        bar2.markDone();
        this.repository.save(bar2);

        List<TodoItem> newall = this.repository.findAll();
        TodoItem todoItem = newall.get(1);
        assertThat(todoItem.getIndex()).isEqualTo(2);
        assertThat(todoItem.getContent()).isEqualTo("bar2");
        assertThat(todoItem.isDone()).isTrue();

    }
}
