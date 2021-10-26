package com.github.ck.todo.cli;

import com.github.ck.todo.cli.repository.FileTodoItemRepository;
import com.github.ck.todo.core.TodoIndexParameter;
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

    @Test
    public void should_mark_todo_item_done() {
        service.addTodoItem(new TodoParameter("foo"));
        cli.execute("done", "1");

        List<TodoItem> list = service.list(true);
        TodoItem todoItem = list.get(0);
        assertThat(list).hasSize(1);
        assertThat(todoItem.getContent()).isEqualTo("foo");
    }

    @Test
    public void should_mark_todo_item_done_by_unknown_index() {
        service.addTodoItem(new TodoParameter("foo"));
        int result = cli.execute("done", "-1");
        assertThat(result).isNotEqualTo(0);

        int result2 = cli.execute("done", "2");
        assertThat(result2).isNotEqualTo(0);

    }

    @Test
    public void should_list() {
        service.addTodoItem(new TodoParameter("foo"));
        service.addTodoItem(new TodoParameter("bar"));
        service.addTodoItem(new TodoParameter("blah"));
        service.markTodoItemDone(new TodoIndexParameter(2));
        int result = cli.execute("list");
        assertThat(result).isEqualTo(0);
    }


    @Test
    public void should_list_all() {
        service.addTodoItem(new TodoParameter("foo"));
        service.addTodoItem(new TodoParameter("bar"));
        service.addTodoItem(new TodoParameter("blah"));
        service.markTodoItemDone(new TodoIndexParameter(2));
        int result = cli.execute("list", "--all");
        assertThat(result).isEqualTo(0);
    }

}
