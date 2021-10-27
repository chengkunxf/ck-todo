package com.github.ck.todo.cli;

import com.github.ck.todo.core.TodoIndexParameter;
import com.github.ck.todo.core.TodoItem;
import com.github.ck.todo.core.TodoItemService;
import com.github.ck.todo.core.TodoParameter;
import com.google.common.base.Strings;
import picocli.CommandLine;

import java.util.List;
import java.util.Optional;

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

    @CommandLine.Command(name = "done")
    public int done(@CommandLine.Parameters(index = "0") final int index) {
        Optional<TodoItem> optionalTodoItem = service.markTodoItemDone(new TodoIndexParameter(index));
        if (!optionalTodoItem.isPresent()) {
            throw new CommandLine.ParameterException(spec.commandLine(), "Unknown todo item index");
        }
        TodoItem todoItem = optionalTodoItem.get();
        System.out.printf("todo done <%d>%n", todoItem.getIndex());
        System.out.printf("Item <%d> done.%n", todoItem.getIndex());
        return 0;
    }

    @CommandLine.Command(name = "list")
    public int list(@CommandLine.Option(names = "--all") final boolean isAll) {
        List<TodoItem> todoItemList = this.service.list(isAll);
        todoItemList.stream().map(this::formatItem).forEach(System.out::println);

        if (isAll) {
            final long doneCount = todoItemList.stream()
                    .filter(TodoItem::isDone)
                    .count();

            System.out.println(formatTotal(todoItemList.size(), doneCount));
            return 0;
        }

        System.out.println(formatTotal(todoItemList.size()));
        return 0;
    }

    private String formatTotal(final int size, final long doneCount) {
        return "Total: "
                + size + unit(size) + ", "
                + doneCount + unit(doneCount);
    }

    private String formatTotal(final int size) {
        return "Total: " + size + unit(size);
    }

    private String unit(final long count) {
        if (count > 1) {
            return " items";
        }

        return " item";
    }


    private String formatItem(final TodoItem todoItem) {
        if (todoItem.isDone()) {
            return String.format("%d. [Done] %s%n", todoItem.getIndex(), todoItem.getContent());
        }
        return String.format("%d. %s%n", todoItem.getIndex(), todoItem.getContent());
    }
}
