package com.github.ck.todo.cli;

import com.github.ck.todo.service.TodoItem;
import com.github.ck.todo.service.TodoItemService;
import com.github.ck.todo.service.TodoParameter;
import picocli.CommandLine;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/26 10:49 上午
 * @description
 */
@CommandLine.Command(name = "todo")
public class TodoCommand {

    private TodoItemService service;

    public TodoCommand(final TodoItemService service) {
        this.service = service;
    }

    @CommandLine.Command(name = "add")
    public int add(@CommandLine.Parameters(index = "0") final String item) {
        TodoItem todoItem = service.addTodoItem(new TodoParameter(item));
        System.out.printf("%d. %s%n", todoItem.getIndex(), todoItem.getContent());
        System.out.printf("Item <%d> added%n", todoItem.getIndex());
        return 0;
    }



}
