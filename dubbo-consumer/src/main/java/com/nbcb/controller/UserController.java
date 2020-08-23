package com.nbcb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.nbcb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("/dubbo")
    @ResponseBody
    public String sayHello(String name){
        return userService.sayHello(name);
    }
}
