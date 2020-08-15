package com.nbcb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * 注册中心Zookeeper
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZookeeperApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperApplication.class,args);
    }
}
