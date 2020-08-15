package com.nbcb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务注册中心
 */
@RestController
public class HelloWorldApplication {
    @GetMapping("/helloworld/{name}")
    public String HelloWorld(@PathVariable String name) {
        return name;
    }
}
