package com.itheima.factory;

import com.itheima.impl.UserDaoImpl;
import com.itheima.service.UserDao;

public class StaticFactory {
    //无参数静态工厂方法
     public  static UserDao getUserDaoStatic(){
         System.out.println("StaticFactory无参构造方法");
         return new UserDaoImpl();
     }

     //有参构造函数工厂方法
    public static UserDao getUserDaoStatic(String str){
        System.out.println("StaticFactory有参构造方法 "  +str);
        return new UserDaoImpl();
    }


}
