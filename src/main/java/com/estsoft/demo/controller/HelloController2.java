package com.estsoft.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * RestController VS Controller 비교
 */
@Controller
public class HelloController2 {

    @GetMapping("/hello2")
    public String newPage() {
        return "hi";    // hi.html
    }
}
