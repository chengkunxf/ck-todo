package com.github.ck.todo.cli.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.github.ck.todo.cli.exception.TodoException;
import com.github.ck.todo.core.TodoItem;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/20 4:57 下午
 * @description
 */
public final class Jsons {

    private static TypeFactory typeFactory = TypeFactory.defaultInstance();
    private static ObjectMapper mapper = new ObjectMapper();

    public static List<TodoItem> readFile(final File file) {
        CollectionType type = typeFactory.constructCollectionType(List.class, TodoItem.class);
        try {
            return mapper.readValue(file, type);
        } catch (IOException e) {
            throw new TodoException("Fail to read TodoItem from file", e);
        }
    }

    public static void writeToFile(final File file, final List<TodoItem> all) {
        try {
            mapper.writeValue(file, all);
        } catch (IOException e) {
            throw new TodoException("Fail to write todoItem objects", e);
        }
    }

    private Jsons() {
    }
}
