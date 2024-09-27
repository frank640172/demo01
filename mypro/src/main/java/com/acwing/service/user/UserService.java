package com.acwing.service.user;

import com.acwing.pojo.User;

public interface UserService {
    public User login(String userCode,String password);
}
