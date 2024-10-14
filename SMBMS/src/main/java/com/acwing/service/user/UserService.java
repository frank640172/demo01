package com.acwing.service.user;

import com.acwing.pojo.User;

import java.sql.Connection;

public interface UserService {
    public User login(String userCode,String password);

    public boolean updatePwd(int id, String password);
}
