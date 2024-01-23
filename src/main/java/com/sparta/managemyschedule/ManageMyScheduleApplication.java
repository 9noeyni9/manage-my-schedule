package com.sparta.managemyschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "com.sparta.managemyschedule")
public class ManageMyScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageMyScheduleApplication.class, args);
    }

}
