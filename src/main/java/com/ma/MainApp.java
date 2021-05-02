package com.ma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MainApp implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }


}
