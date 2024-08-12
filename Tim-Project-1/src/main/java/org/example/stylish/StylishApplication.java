package org.example.stylish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class StylishApplication {

    public static void main(String[] args) {
        SpringApplication.run(StylishApplication.class, args);
    }

}
