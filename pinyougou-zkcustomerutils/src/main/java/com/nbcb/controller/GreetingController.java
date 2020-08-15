package com.nbcb.controller;

import com.nbcb.feign.HelloWorldClient;
import org.apache.curator.x.discovery.ServiceInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GreetingController {

    @Autowired
    private HelloWorldClient helloWorldClient;

    /**
     * 服务器治理发现
     * @param name
     * @return
     */
    @GetMapping("/get-greeting/{name}")
    public String greeting(@PathVariable String name) {
        return helloWorldClient.HelloWorld(name);
    }



}
