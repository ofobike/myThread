package com.nbcb.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class redisTest {

    @Autowired
    private RedisTemplate<String, Object> template;



    @Test
    public void test01(){
        template.opsForValue().set("name","hello");
        Object name = template.opsForValue().get("name");
        System.out.println(name);
    }
}
