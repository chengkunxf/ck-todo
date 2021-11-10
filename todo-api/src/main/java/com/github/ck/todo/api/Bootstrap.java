package com.github.ck.todo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author chengkunxf@126.com
 * @date 2021/11/10 11:35 上午
 * @description
 */
@SpringBootApplication
@EnableJpaRepositories({"com.github.ck.todo.api.repository"})
@EntityScan({"com.github.ck.todo.core"})
public class Bootstrap {
    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }
}
