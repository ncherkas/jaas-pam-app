package com.ncherkas.jaasPamApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
@EnableAutoConfiguration
// @EnableWebSecurity
@ImportResource("classpath:security.xml")
@RestController
public class App {

    @RequestMapping("/")
    public @ResponseBody String home() {
        return "Hello, World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
