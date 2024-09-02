package com.example.redisdemo;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.nio.charset.StandardCharsets;

@SpringBootTest
class RedisDemoApplicationTests {

    @Resource
    private RedisTemplate  redisTemplate;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("name1".getBytes(StandardCharsets.UTF_8),
                "xiaopangzi".getBytes(StandardCharsets.UTF_8));
//
        System.out.println(redisTemplate.opsForValue().get("name1"));
    }

}
