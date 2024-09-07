package com.acwing.pojo;

public class Hello {
    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /*
    依赖注入依赖set进去，去掉就不能生成对应的bean对象
    id类似于变量名，后面的class类似于类
    bean来创建对象
    property通过set方法给属性赋值
     */

    @Override
    public String toString() {
        return "Hello{" +
                "name='" + name + '\'' +
                '}';
    }

}
