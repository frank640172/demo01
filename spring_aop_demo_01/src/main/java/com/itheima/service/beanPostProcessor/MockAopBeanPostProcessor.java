package com.itheima.service.beanPostProcessor;

import com.itheima.service.advice.MyAdvice;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MockAopBeanPostProcessor implements ApplicationContextAware, BeanPostProcessor {

    private  ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
           this.applicationContext =  applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //System.out.println(bean.getClass().getPackage().getName());
        if (bean.getClass().getPackage().getName().equals("com.itheima.service.impl")) {

            System.out.println("1234 " + bean.getClass().getName());
            Object  proxyInstance = Proxy.newProxyInstance(

                    bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    (Object proxy, Method method, Object[] args) ->  {
                            MyAdvice myAdvice = (MyAdvice) applicationContext.getBean("myAdvice");
                            myAdvice.beforeAdvice();
                            Object result = method.invoke(bean,args);
                            myAdvice.afterReturningAdvice();
                            return result;
                        }

            );
            return proxyInstance;
        }
        return bean;
    }

//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
//    }
}
