package com.github.ck.todo.cli;

import com.github.ck.todo.core.TodoIndexParameter;
import com.github.ck.todo.core.TodoItem;
import com.github.ck.todo.core.TodoItemService;
import com.github.ck.todo.core.TodoParameter;
import com.google.common.base.Strings;
import picocli.CommandLine;

import java.util.Optional;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/26 2:48 下午
 * @description
 */
@CommandLine.Command(name = "todo")
public class TodoCommand {

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;
    private TodoItemService service;

    public TodoCommand(final TodoItemService service) {
        this.service = service;
    }

    @CommandLine.Command(name = "add")
    public int add(@CommandLine.Parameters(index = "0") final String item) {
        if (Strings.isNullOrEmpty(item)) {
            throw new CommandLine.ParameterException(spec.commandLine(), "empty item is not allowed");
        }
        TodoItem todoItem = this.service.addTodoItem(new TodoParameter(item));
        System.out.printf("%d. %s%n", todoItem.getIndex(), todoItem.getContent());
        System.out.printf("Item <%d> added%n", todoItem.getIndex());
        return 0;
    }

    @CommandLine.Command(name = "done")
    public int markDone(@CommandLine.Parameters(index = "0") final int index) {
        if (index <= 0) {
            throw new CommandLine.ParameterException(spec.commandLine(), "index should be greater than 0 ");
        }
        Optional<TodoItem> optionalTodoItem = this.service.markTodoItemDone(new TodoIndexParameter(index));
        if (!optionalTodoItem.isPresent()) {
            throw new CommandLine.ParameterException(spec.commandLine(), "unknown todo item index");
        }
        TodoItem todoItem = optionalTodoItem.get();
        System.out.printf("Item <%d> done%n", todoItem.getIndex());

        return 0;
    }
}
