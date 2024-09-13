package com.acwing;


import com.acwing.Demo.Student;
import com.acwing.Demo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MybatisDemo {

    public static void main(String[] args) {

        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        Student student = (Student) ac.getBean("student");
        System.out.println(student.getName());
        System.out.println(student.getAddress());
        System.out.println(student.getScores());
    }
}
