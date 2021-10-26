package com.github.ck.todo.cli;

import com.github.ck.todo.cli.repository.FileTodoItemRepository;
import com.github.ck.todo.service.TodoIndexParameter;
import com.github.ck.todo.service.TodoItem;
import com.github.ck.todo.service.TodoItemService;
import com.github.ck.todo.service.TodoParameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/26 10:53 上午
 * @description
 */
class TodoCommandTest {
    @TempDir
    File tempDir;
    private TodoItemService service;
    private CommandLine cli;

    @BeforeEach
    void setUp() {
        final ObjectFactory factory = new ObjectFactory();
        final File repositoryFile = new File(tempDir, "repository.json");
        this.service = factory.createService(repositoryFile);
        cli = factory.createCommandLine(repositoryFile);
    }

    @Test
    public void should_add_todo_item() {
        int result = cli.execute("add", "foo");
        assertThat(result).isEqualTo(0);

        final List<TodoItem> items = service.list(true);
        assertThat(items).hasSize(1);
        assertThat(items.get(0).getContent()).isEqualTo("foo");
    }

    @Test
    public void should_fail_to_add_empty_todo() {
        int result = cli.execute("add", "");
        assertThat(result).isNotEqualTo(0);
    }

    @Test
    public void should_mark_todo_item_done() {
        service.addTodoItem(new TodoParameter("foo"));
        cli.execute("done", "1");

        final List<TodoItem> items = service.list(true);
        assertThat(items).hasSize(1);
        assertThat(items.get(0).getContent()).isEqualTo("foo");
    }

    @Test
    public void should_mark_todo_done_by_wrong_index() {
        service.addTodoItem(new TodoParameter("foo"));
        assertThat(cli.execute("done", "-1")).isNotEqualTo(0);
        assertThat(cli.execute("done", "2")).isNotEqualTo(0);
    }

    @Test
    public void should_list_without_done() {
        service.addTodoItem(new TodoParameter("foo"));
        service.addTodoItem(new TodoParameter("bar"));
        service.addTodoItem(new TodoParameter("blah"));
        service.markTodoItemDone(new TodoIndexParameter(2));

        assertThat(cli.execute("list")).isEqualTo(0);
    }

    @Test
    public void should_list_with_done() {
        service.addTodoItem(new TodoParameter("foo"));
        service.addTodoItem(new TodoParameter("bar"));
        service.addTodoItem(new TodoParameter("blah"));
        service.markTodoItemDone(new TodoIndexParameter(2));

        assertThat(cli.execute("list", "--all")).isEqualTo(0);
    }
}