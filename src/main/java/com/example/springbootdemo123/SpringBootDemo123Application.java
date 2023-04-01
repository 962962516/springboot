package com.example.springbootdemo123;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class SpringBootDemo123Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemo123Application.class, args);
    }

}
