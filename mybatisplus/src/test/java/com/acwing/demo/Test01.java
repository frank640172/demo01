package com.acwing.demo;

import com.acwing.demo.mapper.UserMapper;
import com.acwing.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Test01 {

    @Autowired
    private UserMapper userMapper;

    @Test
    void test(){
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
