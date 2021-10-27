package com.github.ck.todo.cli;

import com.github.ck.todo.core.TodoItem;
import com.github.ck.todo.core.TodoItemService;
import com.github.ck.todo.core.TodoParameter;
import com.google.common.base.Strings;
import picocli.CommandLine;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/27 10:54 上午
 * @description
 */
@CommandLine.Command(name = "todo")
public class TodoCommand {
    private final TodoItemService service;

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    public TodoCommand(final TodoItemService service) {
        this.service = service;
    }

    @CommandLine.Command(name = "add")
    public int add(@CommandLine.Parameters(index = "0") final String item) {
        if (Strings.isNullOrEmpty(item)) {
            throw new CommandLine.ParameterException(spec.commandLine(), "Null or empty is not allewd");
        }
        TodoItem todoItem = service.addTodoItem(new TodoParameter(item));
        System.out.printf("%d. <%s>%n", todoItem.getIndex(), todoItem.getContent());
        System.out.printf("Item <%d> added%n", todoItem.getIndex(), todoItem.getContent());
        return 0;
    }
}
