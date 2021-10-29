package com.github.ck.todo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author chengkunxf@126.com
 * @date 2021/10/29 11:53 上午
 * @description
 */
@SpringBootApplication
@EnableJpaRepositories("com.github.ck.todo.api.repository")
@EntityScan("com.github.ck.todo.core")
@ComponentScan("com.github.ck.todo")
public class Bootstrap {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(Bootstrap.class, args);
    }

    public static void shutdown() {
        context.close();
    }
}
