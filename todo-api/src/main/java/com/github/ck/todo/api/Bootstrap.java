package com.github.ck.todo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

/**
 * @author chengkunxf@126.com
 * @date 2021/11/10 11:35 上午
 * @description
 */
@SpringBootApplication
@EnableJpaRepositories("com.github.ck.todo.core")
@EntityScan("com.github.ck.todo.core")
@ComponentScan("com.github.ck.todo")
public class Bootstrap {
    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }
}
