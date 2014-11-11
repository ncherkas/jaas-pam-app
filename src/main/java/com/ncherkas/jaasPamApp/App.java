package com.ncherkas.jaasPamApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
@EnableAutoConfiguration
@ImportResource("classpath:security.xml")
@RestController
public class App {

    @RequestMapping("/")
    public String home() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal instanceof UserDetails
                ? ((UserDetails) principal).getUsername()
                : principal.toString();
        return "Hello, " + username + "!";
    }

    @RequestMapping("/info")
    public String info() {
        return "Info";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
