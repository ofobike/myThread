package com.nbcb.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.nbcb.service.UserService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 开启注解远程发布服务失效(指定使用cglib代理方式,指定interfaceClass的接口名称)
 */
@Service(interfaceClass = UserService.class,protocol = "dubbo")
@Transactional
public class UserServiceImpl implements UserService {
    @Override
    public String sayHello(String name) {
        return "Hello"+name;
    }
}
