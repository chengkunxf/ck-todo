package com.github.ck.todo.cli.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.github.ck.todo.cli.exception.TodoItemException;
import com.github.ck.todo.service.TodoItem;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/25 3:53 下午
 * @description
 */
public final class Jsons {

    private static TypeFactory typeFactory = TypeFactory.defaultInstance();
    private static ObjectMapper mapper = new ObjectMapper();

    public static void writeToFile(final File file, final List<TodoItem> collect) {
        try {
            mapper.writeValue(file, collect);
        } catch (IOException e) {
            throw new TodoItemException("Fail to write data to file", e);
        }
    }

    public static List<TodoItem> readFromFile(final File file) {
        CollectionType collectionType = typeFactory.constructCollectionType(List.class, TodoItem.class);
        try {
            return mapper.readValue(file, collectionType);
        } catch (IOException e) {
            throw new TodoItemException("Fail to read data from file", e);
        }
    }


    private Jsons() {
    }
}
