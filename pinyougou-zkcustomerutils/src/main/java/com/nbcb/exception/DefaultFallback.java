package com.nbcb.exception;

import com.nbcb.feign.HelloWorldClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class DefaultFallback implements FallbackFactory<HelloWorldClient.TheClient > {
    @Override
    public HelloWorldClient.TheClient create(Throwable throwable) {
        return new HelloWorldClient.TheClient() {
            @Override
            public String HelloWorld(String name) {
                return "连接超时!";
            }
        };
    }
}
