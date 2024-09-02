package JDBC;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class DataSource_Demo_02 {



    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(DataSource_Demo_02.class.getClassLoader().getResourceAsStream("druid.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            dataSource.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
