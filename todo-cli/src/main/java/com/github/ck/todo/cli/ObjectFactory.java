package com.github.ck.todo.cli;

import com.github.ck.todo.cli.repository.FileTodoItemRepository;
import com.github.ck.todo.service.TodoItemRepository;
import com.github.ck.todo.service.TodoItemService;
import picocli.CommandLine;

import java.io.File;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/26 10:44 上午
 * @description
 */
public class ObjectFactory {

    public CommandLine createCommandLine(final File repositoryFile) {
        TodoCommand todoCommand = createTodoCommand(repositoryFile);
        return new CommandLine(createTodoCommand(repositoryFile));
    }

    public TodoCommand createTodoCommand(final File repositoryFile) {
        TodoItemService service = createService(repositoryFile);
        return new TodoCommand(service);
    }

    public TodoItemService createService(final File repositoryFile) {
        TodoItemRepository repository = new FileTodoItemRepository(repositoryFile);
        return new TodoItemService(repository);
    }
}
