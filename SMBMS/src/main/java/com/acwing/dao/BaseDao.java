package com.acwing.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {

    private  static String driver;
    private  static String username;
    private  static String password;
    private  static String url;

    static {
        InputStream in = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        Properties  properties =  new Properties();
        try {
            properties.load(in);
            driver = properties.getProperty("driver");
            url=properties.getProperty("url");
            password=properties.getProperty("password");
            username=properties.getProperty("username");
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取数据库链接
    public static Connection getConnection(){
        Connection con = null;
        try {
            System.out.println(driver);
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    //编写查询公共类
    public  static  ResultSet excute(Connection con,String sql,Object[] params,PreparedStatement ps,ResultSet rs) throws Exception {
            ps = con.prepareStatement(sql);
            //弄参数的,obj从1开始
            for(int i = 0 ; i < params.length; i++){
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();

        return rs;
    }

    //编写增删改工具类
    public  static  int excute(Connection con,String sql,Object[] params,PreparedStatement ps) throws Exception {
        ps = con.prepareStatement(sql);
        //弄参数的,obj从1开始
        for(int i = 0 ; i < params.length; i++){
            ps.setObject(i + 1, params[i]);
        }
        int updateLine = ps.executeUpdate();

        return updateLine;
    }

    public static Boolean closeResource(Connection con,PreparedStatement ps,ResultSet rs){
            Boolean flag = true;
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException e) {
                    flag = false;
                    e.printStackTrace();
                }
            }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                flag = false;
                e.printStackTrace();
            }
        }
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                flag = false;
                e.printStackTrace();
            }
        }
            return flag;
    }
}
