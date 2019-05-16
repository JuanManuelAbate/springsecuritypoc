package com.example.springsecuritypoc.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class PocController {


    @GetMapping("/secure")
    public String secureMethod() {

        //Here we return the principal that is in the SecurityContext just to show how it works.
        return "I'm secure" + " " +SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("/insecure")
    public String insecureMethod() {
        return "I'm insecure";
    }
}
