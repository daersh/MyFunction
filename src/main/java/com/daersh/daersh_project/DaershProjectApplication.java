package com.daersh.daersh_project;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.TimeZone;

@SpringBootApplication
@Slf4j
public class DaershProjectApplication {
    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        log.info("start time: " + LocalDateTime.now());

    }

    public static void main(String[] args) {
        SpringApplication.run(DaershProjectApplication.class, args);
    }
}
