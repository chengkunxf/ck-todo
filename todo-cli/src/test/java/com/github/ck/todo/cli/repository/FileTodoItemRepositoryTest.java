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
}
