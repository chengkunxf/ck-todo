package com.github.ck.todo.api.repository;

import com.github.ck.todo.core.TodoItem;
import com.github.ck.todo.core.TodoItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author chengkunxf@126.com
 * @date 2021/11/1 5:26 下午
 * @description
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:test.properties")
public class TodoItemRepositoryTest {

    @Autowired
    private TodoItemRepository repository;

    @Test
    public  void should_find_nothing_for_empty_repository(){
        List<TodoItem> all = repository.findAll();
        assertThat(all).hasSize(0);
    }
}
