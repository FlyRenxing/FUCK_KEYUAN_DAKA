package com.example.fuckdaka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FuckDaKaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FuckDaKaApplication.class, args);
    }

}
