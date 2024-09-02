package com.itheima.test;

import com.itheima.service.UserSerivce;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
//
//        reader.loadBeanDefinitions("beans.xml");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");


        Object userService = applicationContext.getBean("userService");



        System.out.println(userService);
    }
}
