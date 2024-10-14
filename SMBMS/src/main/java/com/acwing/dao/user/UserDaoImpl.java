package com.acwing.dao.user;

import com.acwing.dao.BaseDao;
import com.acwing.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
                    user.setGender(resultSet.getInt("gender"));
                    user.setAddress(resultSet.getString("address"));
                    user.setId(resultSet.getInt("id"));
                    user.setUserCode(resultSet.getString("userCode"));
                    user.setUserName(resultSet.getString("userName"));
                    user.setUserPassword(resultSet.getString("userPassword"));
                    user.setBirthday(resultSet.getDate("birthday"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setUserRole(resultSet.getInt("userRole"));
                    user.setModifyDate(resultSet.getDate("modifyDate"));
                    user.setCreatedBy(resultSet.getInt("createdBy"));
                    user.setModifyBy(resultSet.getInt("setModifyBy"));
                    user.setCreationDate(resultSet.getTimestamp("creationDate"));
                    user.setModifyDate(resultSet.getTimestamp("modifyDate"));
                }
                BaseDao.closeResource(null, preparedStatement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public int updatePwd(Connection con, int id, String password) throws Exception {
         PreparedStatement pst= null;
         int res = 0;
         String sql="update smbms_user set userPassword=? where id =?";
         System.out.println("UserDaoImpl updatePwd()" + " id= " + id + ", password= " + password);
         Object[] params={password,id};
         if(con!=null) {
             res = BaseDao.excute(con, sql, params, pst);

             BaseDao.closeResource(null, pst, null);

         }
        return res;
    }
}
