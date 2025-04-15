package com.estsoft.demo.controller;

import com.estsoft.demo.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {   // 의존성 주입, 의존성 주입의 선행 조건: 빈 등록
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello() {
        return helloService.sayHello(); // "Hello Spring!" 출력
    }
}
