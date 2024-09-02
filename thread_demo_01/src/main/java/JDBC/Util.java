package JDBC;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class Util {


    public  static void close(Connection conn, Statement statement, ResultSet resultSet){
        if(resultSet!= null) {
            try {
               resultSet.close();
            }
      catch (SQLException e) {
            e.printStackTrace();
        }
        }
        if(statement!=null) {
            try {
                statement.close();
            }
            catch (SQLException e) {
            e.printStackTrace();
            }
        }

        try {
            if(conn!= null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  static  void close(Connection conn, Statement statement){
        try {
            if(statement!=null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(conn!= null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  static Connection connection(){
        Connection conn = null;
        InputStream inputStream = null;
        ClassLoader classLoader = Util.class.getClassLoader();
        URL resource = classLoader.getResource("driver.properties");

        System.out.println(resource);
        if(resource!=null){

            try {
                inputStream = resource.openStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Properties properties = new Properties();
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            System.out.println(url + " " + username + " " + password);
            try {
                conn = DriverManager.getConnection(url,username,password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return conn;
    }
}
