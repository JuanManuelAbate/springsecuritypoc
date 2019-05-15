package com.example.springsecuritypoc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class PocController {

    @GetMapping("/secure")
    public String secureMethod() {
        return "I'm secure";
    }

    @GetMapping("/insecure")
    public String insecureMethod() {
        return "I'm insecure";
    }
}
