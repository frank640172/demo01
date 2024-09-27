package com.acwing.dao.user;

import com.acwing.pojo.User;

import java.sql.Connection;

public interface UserDao {
    public User getLoginUser(Connection con,String userCode);
}
