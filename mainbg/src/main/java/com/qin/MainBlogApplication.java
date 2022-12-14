package com.qin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@SpringBootApplication
@MapperScan("com.qin.mapper")
public class MainBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainBlogApplication.class, args);
    }
}
