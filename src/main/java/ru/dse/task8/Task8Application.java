package ru.dse.task8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Task8Application {

    public static void main(String[] args) {
        SpringApplication.run(Task8Application.class, args);
    }
}
