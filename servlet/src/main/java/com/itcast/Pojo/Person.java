package com.itcast.Pojo;

import java.util.Objects;

public class Person {
    private  String  name;
    public  int age;
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void show(){
        System.out.println("你好，我是一个人");
    }

    public void showSex(String sex){
        System.out.println("我的性别是："+sex);
    }

    private String showNation(String nation){
        System.out.println("我的国籍是："+nation);
        return nation;
    }
}
