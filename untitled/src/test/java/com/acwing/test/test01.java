package com.acwing.test;


import com.acwing.Demo.Brand;

import com.acwing.Demo.User;
import com.acwing.Mapper.BrandMapper;
import com.acwing.Mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class test01 {
    @Autowired
    private UserMapper userMapper;

    @Test
    void test_01() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAll();
        System.out.println(brands);
        sqlSession.close();
    }

    @Test
    void test_02() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = mapper.selectById(2);
        System.out.println(brand);

        sqlSession.close();
    }

    @Test
    void test_03() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        Map map = new HashMap();

        Integer status = 1;
        String companyName = "小米";
        String brandName = "小米";
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";
        map.put("company_name",companyName);
        map.put("brand_name",brandName);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //List<Brand> brand = mapper.selectByCondition(status,companyName,brandName);
        List<Brand> brands = mapper.selectByCondition(map);
        System.out.println(brands);

        sqlSession.close();
    }

    @Test
    void test_04() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        Brand brand = new Brand();
        brand.setBrand_name("apple");
        brand.setCompany_name("apple");
        brand.setDescription("苹果公司");
        brand.setOrdered(300);
        brand.setStatus(4);

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.add(brand);

        //sqlSession.commit();//手动提交事务
        sqlSession.close();

    }

    @Test
    void test_update() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        Brand brand = new Brand();
        brand.setBrand_name("microsoft");
        brand.setCompany_name("microsoft");
        brand.setDescription("微软公司");
        brand.setOrdered(240);
        brand.setStatus(15);
        brand.setId(9);
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.update(brand);

        //sqlSession.commit();//手动提交事务
        sqlSession.close();

    }



    @Test
    void test_mybatis_plus_select_all(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

}
