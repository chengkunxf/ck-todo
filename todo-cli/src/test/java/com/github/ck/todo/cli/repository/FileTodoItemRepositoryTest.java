package com.github.ck.todo.cli.repository;

import com.github.ck.todo.core.TodoItem;
import com.google.common.collect.Iterables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/13 3:57 下午
 * @description
 */
public class FileTodoItemRepositoryTest {

    @TempDir
    File tempDir;
    private FileTodoItemRepository repository;
    private File tempFile;

    @BeforeEach
    private void setUp() throws IOException {
        this.tempFile = File.createTempFile("file", "", tempDir);
        this.repository = new FileTodoItemRepository(this.tempFile);
    }

    @Test
    public void should_find_nothing_for_empty_repository() {
        List<TodoItem> all = repository.findAll();
        assertThat(all).hasSize(0);
    }

    @Test
    public void should_find_saved_items() {
        repository.save(new TodoItem("foo"));
        repository.save(new TodoItem("bar"));
        List<TodoItem> items = repository.findAll();
        assertThat(items).hasSize(2);
        final TodoItem firstItem = items.get(0);
        assertThat(firstItem.getContent()).isEqualTo("foo");
        assertThat(firstItem.getIndex()).isEqualTo(1);
        final TodoItem secondItem = items.get(1);
        assertThat(secondItem.getContent()).isEqualTo("bar");
        assertThat(secondItem.getIndex()).isEqualTo(2);
    }
}
