package com.day4.spring.security.task1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class HelloSSLControler {
    @GetMapping("/secure-hello")
    public String secureHello() {
        return "Hello, Secure World!";
    }
}
