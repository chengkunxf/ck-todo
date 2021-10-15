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
 * @date 2021/10/15 2:38 下午
 * @description
 */
public class FileTodoItemRepositoryTest {

    @TempDir
    File tempDir;
    private File tempFile;
    private FileTodoItemRepository repository;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("file","",tempDir);
        repository = new FileTodoItemRepository(tempFile);
    }

    @Test
    public void should_find_nothing_from_temp_repository(){
        List<TodoItem> all = repository.findAll();
        assertThat(all).hasSize(0);
    }

}
