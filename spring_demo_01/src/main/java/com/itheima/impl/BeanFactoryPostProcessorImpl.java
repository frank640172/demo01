package com.itheima.impl;


import org.springframework.beans.BeansException;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

//public class BeanFactoryPostProcessorImpl implements BeanFactoryPostProcessor {
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        System.out.println("MyBeanFactoryPostProcessor的postProcessBeanFactory方法执行了");
////        BeanDefinition userServiceBD = beanFactory.getBeanDefinition("userService");
////        userServiceBD.setBeanClassName("com.itheima.impl.UserDaoImpl");
//        if(beanFactory instanceof DefaultListableBeanFactory){
//            DefaultListableBeanFactory beanFactory1 = (DefaultListableBeanFactory) beanFactory;
//            BeanDefinition beanDefinition = new RootBeanDefinition();
//            beanDefinition.setBeanClassName("com.itheima.impl.UserDaoImpl");
//            beanFactory1.registerBeanDefinition("userService",beanDefinition);
//        }
//    }
//}
public class BeanFactoryPostProcessorImpl implements  BeanDefinitionRegistryPostProcessor{

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("postProcessBeanDefinitionRegistry is excuted");
        BeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClassName("com.itheima.impl.UserDaoImpl");
        registry.registerBeanDefinition("userService",beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        BeanDefinitionRegistryPostProcessor.super.postProcessBeanFactory(beanFactory);
        System.out.println("postProcessBeanFactory is executed");
    }
}


