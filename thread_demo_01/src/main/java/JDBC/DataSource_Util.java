package JDBC;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataSource_Util {

    private static DataSource dataSource;

    //预先加载好对应的dataSource
    static {
        Properties properties = new Properties();
        InputStream in = DataSource_Util.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(in);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
         return dataSource.getConnection();
    }

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

    public  static void close(Connection conn, Statement statement){
        close(conn,statement,null);
    }


    public static DataSource getDataSource() {
        return dataSource;
    }
}
