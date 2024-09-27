package com.acwing.dao.user;

import com.acwing.dao.BaseDao;
import com.acwing.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao{
    @Override
    public User getLoginUser(Connection con, String userCode) {

        PreparedStatement  preparedStatement = null;
        ResultSet  resultSet = null;
        User user = null;
        if(con!=null) {
            try {
                String sql="select *  from smbms_user where userCode=?";
                Object[] params={userCode};
                resultSet = BaseDao.excute(con, sql, params, preparedStatement, resultSet);

                if(resultSet.next()){
                    user = new  User();
                    user.setAddress(resultSet.getString("address"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
