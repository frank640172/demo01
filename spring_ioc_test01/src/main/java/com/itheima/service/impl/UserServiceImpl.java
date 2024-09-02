package com.itheima.service.impl;

import com.itheima.service.UserService;


public class UserServiceImpl implements UserService {

    private UserDao userDao;
    public static void hello(String name) {
        System.out.println("hello,world "  + name);
    }


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
