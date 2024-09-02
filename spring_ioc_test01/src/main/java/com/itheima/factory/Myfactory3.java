package com.itheima.factory;

import com.itheima.service.impl.UserDao;
import com.itheima.service.impl.UserDaoImpl;
import org.springframework.beans.factory.FactoryBean;

public class Myfactory3 implements FactoryBean<UserDao> {
    @Override
    public UserDao getObject() throws Exception {
        return new UserDaoImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }
}
