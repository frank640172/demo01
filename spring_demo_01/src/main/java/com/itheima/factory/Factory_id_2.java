package com.itheima.factory;

import com.itheima.impl.UserDaoImpl;
import com.itheima.service.UserDao;

public class Factory_id_2 {
      public UserDao getUserDao(){
          System.out.println("Factory_id_2 " + "无参工厂方法");
          return new UserDaoImpl();
      }

      public  UserDao getUserDao(String str){
          System.out.println("Factory_id_2 " + "有参工厂方法");
          return new UserDaoImpl();
      }
}
