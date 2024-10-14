package com.acwing.service.user;

import com.acwing.dao.BaseDao;
import com.acwing.dao.user.UserDao;
import com.acwing.dao.user.UserDaoImpl;
import com.acwing.pojo.User;

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

    @Override
    public boolean updatePwd(int id, String password) {
        Connection conn = null;
        conn=BaseDao.getConnection();
        boolean flag=false;
        try {
            int res = userDao.updatePwd(conn,id,password);
            if(res > 0) flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(conn,null,null);
        }
        return flag;
    }

}
