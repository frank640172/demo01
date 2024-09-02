package com.acwing;


import com.acwing.Demo.Brand;
import com.acwing.Demo.User;
import com.acwing.Mapper.BrandMapper;
import com.acwing.Mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@MapperScan("com.acwing.Mapper")
public class MybatisDemo {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

//        List<User> list = sqlSession.selectList("user.selectAll");
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        List<User> users = mapper.selectAll();
//        System.out.println(users);
          BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
          List<Brand> brands = mapper.selectAll();
          System.out.println(brands);
          sqlSession.close();

    }
}
