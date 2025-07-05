package com.task.yourcsguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
@EnableJpaAuditing
public class YourCsGuideApplication {

    public static void main(String[] args) {
        SpringApplication.run(YourCsGuideApplication.class, args);
    }

}
