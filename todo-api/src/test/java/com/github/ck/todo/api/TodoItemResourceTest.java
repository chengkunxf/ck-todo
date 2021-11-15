package com.github.ck.todo.api;

import com.github.ck.todo.core.TodoItem;
import com.github.ck.todo.core.TodoItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author chengkunxf@126.com
 * @date 2021/11/10 3:03 下午
 * @description
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource("classpath:test.properties")
public class TodoItemResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoItemRepository repository;

    @Test
    public void should_add_item() throws Exception {
        String todoItem = "{ " +
                "\"content\": \"foo\"" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/todo-items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoItem))
                .andExpect(status().isCreated());

        assertThat(repository.findAll()).anyMatch(item -> item.getContent().equals("foo"));
    }

    @Test
    public void should_fail_to_add_empty_item() throws Exception {
        String todoItem = "{ " +
                "\"content\": \"\"" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/todo-items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoItem))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_mark_as_done() throws Exception {
        final TodoItem newItem = repository.save(new TodoItem("foo"));
        assertThat(newItem.isDone()).isFalse();

        String done = "{ \"done\": true }";

        System.out.println("Before mark as done:" + newItem.getIndex());
        mockMvc.perform(MockMvcRequestBuilders.put("/todo-items/" + newItem.getIndex())
                .contentType(MediaType.APPLICATION_JSON)
                .content(done))
                .andExpect(status().isOk());

        assertThat(repository.findAll()).anyMatch(item -> item.getContent().equals("foo") && item.isDone());
    }

    @Test
    public void should_fail_to_mark_with_out_of_index() throws Exception {
        String done = "{ \"done\": true }";

        mockMvc.perform(MockMvcRequestBuilders.put("/todo-items/-1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(done))
                .andExpect(status().isBadRequest());
    }


}
