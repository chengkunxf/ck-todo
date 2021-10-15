package com.github.ck.todo.cli.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.github.ck.todo.cli.exception.TodoException;
import com.github.ck.todo.core.TodoItem;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/15 11:04 上午
 * @description
 */
public final class Jsons {

    private static TypeFactory typeFactory = TypeFactory.defaultInstance();
    private static ObjectMapper mapper = new ObjectMapper();

    public static void writeToFile(final File file, final List<TodoItem> all) {
        try {
            mapper.writeValue(file, all);
        } catch (IOException e) {
            throw new TodoException("fail to write todo item", e);
        }
    }

    public static List<TodoItem> readFromFile(final File file) {
        try {
            CollectionLikeType type = typeFactory.constructCollectionType(List.class, TodoItem.class);
            return mapper.readValue(file, type);
        } catch (IOException e) {
            throw new TodoException("fail to read todoItem", e);
        }
    }

    private Jsons() {

    }
}
