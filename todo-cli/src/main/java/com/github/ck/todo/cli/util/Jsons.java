package com.github.ck.todo.cli.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.github.ck.todo.cli.TodoException;
import com.github.ck.todo.core.TodoItem;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/14 9:57 上午
 * @description
 */
public class Jsons {


    public static List<TodoItem> fileToObjects(File file, ObjectMapper mapper, TypeFactory typeFactory) {
        try {
            final CollectionType type = typeFactory.constructCollectionType(List.class, TodoItem.class);
            return mapper.readValue(file, type);
        } catch (IOException e) {
            throw new TodoException("Fail to read todo items", e);
        }
    }
}
