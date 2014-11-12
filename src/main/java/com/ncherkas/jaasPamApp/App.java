package com.ncherkas.jaasPamApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot REST API secured by JAAS PAM module.
 * To start application:
 *  1) download JPam library from http://jpam.sourceforge.net/
 *  2) copy PAM config file "net-df-jpam" into directory /etc/pam.d and edit as it's shown in the JPam documentation
 *      http://jpam.sourceforge.net/documentation/configuration.html
 *  3) pass VM option -Djava.library.path=<directory containing "libjpam.so"> (or consider other JNI linking option)
 */
@EnableAutoConfiguration
@ImportResource("classpath:security.xml") // security configuration, note that filter is to be attached in web.xml
@RestController
public class App {

    @RequestMapping("/")
    public String home() {
        /**
         * Getting user data
         */
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal instanceof UserData
                ? ((UserData) principal).getUsername()
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
