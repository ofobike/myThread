package com.nbcb.domain.test;

import com.alibaba.fastjson.JSON;
import com.nbcb.domain.User;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.SocketTimeoutException;
import java.util.Iterator;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplateFlag;

    @Test
    public void test01(){
        Set keys = redisTemplateFlag.keys("*");
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
        }
    }

    @Test
    public void test02(){
        User user = new User("奥巴马",45);
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        String string = JSON.toJSONString(user);
        redisTemplateFlag.opsForValue().set("user",string);
        System.out.println(redisTemplateFlag.opsForValue().get("user"));
    }

    @Test
    public void test03(){
        Object name = redisTemplateFlag.opsForValue().get("name");
        System.out.println(name);
    }



}
