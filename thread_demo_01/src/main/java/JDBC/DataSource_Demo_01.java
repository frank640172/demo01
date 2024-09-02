package JDBC;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource_Demo_01 {

    public static void main(String[] args) {
        DataSource dataSource = new ComboPooledDataSource();
        try {
            for(int i = 0 ; i < 11; i++) {
                Connection conn = dataSource.getConnection();
                System.out.println(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
