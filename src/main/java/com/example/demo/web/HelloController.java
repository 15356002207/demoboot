package com.example.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/upload")
public class HelloController {
//@ResponseBody
    @GetMapping("/hello")
    public String hello() {
        System.out.println("system 调用接口");
        log.info("loginfo 调用接口");
        return "Hello Spring Boot!";
    }

}