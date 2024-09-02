package com.itheima.factory;

import com.itheima.impl.UserDaoImpl;
import com.itheima.service.UserDao;
import org.springframework.beans.factory.FactoryBean;

public class Factory_id_3 implements FactoryBean<UserDao> {
    @Override
    public UserDao getObject() throws Exception {
        return new UserDaoImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }
}
