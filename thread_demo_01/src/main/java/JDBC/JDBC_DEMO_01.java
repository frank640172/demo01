package JDBC;



import java.sql.*;

public class JDBC_DEMO_01 {


    public static void main(String[] args)  {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取数据库连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis", "root", "2656175yanbo");
            //定义sql语句
//          String sql1 = "INSERT INTO tb_user (username, password,gender,addr) VALUES ('zh','123','女','河北')";
            String sql2 = "UPDATE tb_user SET password = '1234' where username = 'xnn' " ;
//          String sql3 = "";
            statement = conn.createStatement();
            statement.executeUpdate(sql2);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(resultSet!=null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }






//

    }
}
