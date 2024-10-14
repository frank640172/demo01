package com.acwing.dao.user;

import com.acwing.pojo.User;

import java.sql.Connection;

public interface UserDao {
    public User getLoginUser(Connection con,String userCode);

    /*
    修改密码：首先得到用户是谁，之后修改密码，需要得到新密码，老密码
    老的密码和用户密码比较，如果不一致则返回主页
    如果一致，更新操作
     */
    public int updatePwd(Connection con,int id, String password) throws Exception;
}
