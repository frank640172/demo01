package JDBC;

import org.junit.jupiter.api.Test;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

public class JDBC_Template {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSource_Util.getDataSource());
@Test
   public void test1() {
       String sql = "update tb_user set password = '1111' where username = ? ";
       int cnt = jdbcTemplate.update(sql, "xnn");
       System.out.println(cnt);
   }

   @Test
    public void test2(){
        String sql = "delete from tb_user where username = ?";
        int cnt = jdbcTemplate.update(sql,"xnn");
        System.out.println(cnt);
   }

   @Test
    public void test3(){
        String sql = "insert into tb_user (id,username,password,gender,addr)" +
                "values (?,?,?,?,?)";
        int cnt  = jdbcTemplate.update(sql,null,"xnn","1111","女","乌鲁木齐");
       System.out.println(cnt);
   }
   //以上增删改操作均由update操作即可，后面查询由别的操作来做
    @Test
    public void test4(){
        String sql = "select * from tb_user where id = ?";
        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap(sql,1);
        System.out.println(stringObjectMap.keySet());
    }

}
