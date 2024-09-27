package com.acwing.service.user;

import com.acwing.dao.BaseDao;
import com.acwing.dao.user.UserDao;
import com.acwing.dao.user.UserDaoImpl;
import com.acwing.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class UserServiceImpl implements UserService{

    private UserDao userDao;

    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    @Override
    public User login(String userCode, String password) {
        Connection conn = null;
        User user = null;
        conn = BaseDao.getConnection();
        user = userDao.getLoginUser(conn, userCode);
        BaseDao.closeResource(conn,null,null);
        return user;
    }

}
