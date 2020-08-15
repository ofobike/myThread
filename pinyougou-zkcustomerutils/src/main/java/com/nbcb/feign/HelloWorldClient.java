package com.nbcb.feign;


import com.nbcb.exception.DefaultFallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Configuration
@EnableFeignClients
@EnableDiscoveryClient

@SuppressWarnings("all")
public class HelloWorldClient {

    @Autowired
    private TheClient theClient;

    /**
     * 示例数据
     */
    @FeignClient(name = "zookeeperApplication",fallbackFactory = DefaultFallback.class)
    public interface TheClient {
        @RequestMapping(path = "/helloworld/{name}", method = RequestMethod.GET)
        @ResponseBody
        String HelloWorld(@PathVariable(name = "name") String name);
    }

    public String HelloWorld(String name) {
        return theClient.HelloWorld(name);
    }
}
