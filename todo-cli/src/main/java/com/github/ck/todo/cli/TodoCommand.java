package com.github.ck.todo.cli;

import com.github.ck.todo.service.TodoIndexParameter;
import com.github.ck.todo.service.TodoItem;
import com.github.ck.todo.service.TodoItemService;
import com.github.ck.todo.service.TodoParameter;
import com.google.common.base.Strings;
import picocli.CommandLine;

import java.util.Optional;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/26 10:49 上午
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
        TodoItem todoItem = service.addTodoItem(new TodoParameter(item));
        System.out.printf("%d. %s%n", todoItem.getIndex(), todoItem.getContent());
        System.out.printf("Item <%d> added%n", todoItem.getIndex());
        return 0;
    }

    @CommandLine.Command(name = "done")
    public int done(@CommandLine.Parameters(index = "0") final int index) {
        if (index <= 0) {
            throw new CommandLine.ParameterException(spec.commandLine(), "index should be greater than 0");
        }
        Optional<TodoItem> optionalTodoItem = service.markTodoItemDone(new TodoIndexParameter(index));
        if (!optionalTodoItem.isPresent()) {
            throw new CommandLine.ParameterException(spec.commandLine(), "unknow todo item index");
        }
        final TodoItem actual = optionalTodoItem.get();
        System.out.printf("Item <%d> done%n", actual.getIndex());
        return 0;
    }


}
