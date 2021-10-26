package com.github.ck.todo.cli;

import com.github.ck.todo.cli.repository.FileTodoItemRepository;
import com.github.ck.todo.core.TodoItem;
import com.github.ck.todo.core.TodoItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/26 2:42 下午
 * @description
 */
public class TodoCommandTest {

    @TempDir
    File tempDir;
    private TodoItemService service;
    private TodoCommand todoCommand;
    private CommandLine cli;

    @BeforeEach
    void setUp() {
        File repositoryFile = new File(tempDir, "repository.json");
        FileTodoItemRepository repository = new FileTodoItemRepository(repositoryFile);
        service = new TodoItemService(repository);
        todoCommand = new TodoCommand(service);
        cli = new CommandLine(todoCommand);
    }

    @Test
    public void should_add_todo_item() {
        int result = cli.execute("add", "foo");
        assertThat(result).isEqualTo(0);

        List<TodoItem> list = service.list(true);
        assertThat(list).hasSize(1);
    }

    @Test
    public void should_add_todo_item_by_empty() {
        int result = cli.execute("add", "");
        assertThat(result).isNotEqualTo(0);
    }

}
