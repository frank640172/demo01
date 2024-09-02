package JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_DEMO_02 {

    public static void main(String[] args) {
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
             con = Util.connection();
             statement = con.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "select * from tb_user";
        try {
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                System.out.println(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Util.close(con,statement,resultSet);
    }


}
