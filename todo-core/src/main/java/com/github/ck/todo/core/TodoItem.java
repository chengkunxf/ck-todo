package com.github.ck.todo.core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/12 10:58 上午
 * @description
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "todo_items")
public class TodoItem {

    @Column
    private String content;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int index;

    @Column
    private boolean done;

    public TodoItem(final String content) {
        this.content = content;
        this.done = false;
    }

    public void assignIndex(final int index) {
        this.index = index;
    }

    public void markDone() {
        this.done = true;
    }
}
