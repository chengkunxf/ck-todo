package com.github.ck.todo.cli;

import com.github.ck.todo.cli.repository.FileTodoItemRepository;
import com.github.ck.todo.core.TodoItem;
import com.github.ck.todo.core.TodoItemService;
import com.github.ck.todo.core.TodoParameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/27 10:49 上午
 * @description
 */
public class TodoCommandTest {

    @TempDir
    File tempDir;
    File repositoryFile;
    FileTodoItemRepository repository;
    TodoItemService service;
    TodoCommand todoCommand;
    CommandLine cli;

    @BeforeEach
    void setUp() {
        File repositoryFile = new File(tempDir, "repository.json");
        repository = new FileTodoItemRepository(repositoryFile);
        service = new TodoItemService(repository);
        todoCommand = new TodoCommand(service);
        cli = new CommandLine(todoCommand);
    }

    @Test
    public void should_add_todo_item(){
        int result = cli.execute("add", "foo");
        assertThat(result).isEqualTo(0);

        List<TodoItem> list = service.list(true);
        TodoItem todoItem = list.get(0);
        assertThat(todoItem.getContent()).isEqualTo("foo");
    }

    @Test
    public void should_add_todo_item_by_null(){
        int result = cli.execute("add", "");
        assertThat(result).isNotEqualTo(0);
    }

    @Test
    public void should_mark_todo_item_done(){
        service.addTodoItem(new TodoParameter("foo"));
        int result = cli.execute("done", "1");
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void should_mark_todo_item_done_by_wrong_index(){
        service.addTodoItem(new TodoParameter("foo"));
        int result = cli.execute("done", "2");
        assertThat(result).isNotEqualTo(0);
    }
}
