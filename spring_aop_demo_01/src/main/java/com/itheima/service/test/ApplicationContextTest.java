package com.itheima.service.test;

import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        UserServiceImpl userService = (UserServiceImpl) applicationContext.getBean("userService");
        userService.show1();
        userService.show2();

    }
}
