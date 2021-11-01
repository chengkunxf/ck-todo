package com.github.ck.todo.core;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/22 2:43 下午
 * @description
 */
@Getter
@Entity
@Table(name = "todo_items")
public class TodoItem {

    @Column
    private final String content;

    @Column
    private boolean done;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int index;

    public TodoItem(final String content) {
        this.content = content;
    }

    public void markDone() {
        this.done = true;
    }

    public void assignIndex(final int index) {
        this.index = index;
    }
}
