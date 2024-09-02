package com.itheima;

import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    public static void main(String[] args) {
        //创建工厂对象
        DefaultListableBeanFactory beanFactory =new DefaultListableBeanFactory();
        //创建读取器，读xml文件
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        ApplicationContext applicationContext =  new ClassPathXmlApplicationContext();

        //读取xml文件给工厂
         xmlBeanDefinitionReader.loadBeanDefinitions("beans.xml");
        //根据id获取bean对象
        UserService userService = (UserService)beanFactory.getBean("userService");

        UserServiceImpl.hello();

    }
}
