package Synchronize;

import lombok.SneakyThrows;

import java.util.concurrent.locks.ReentrantLock;

/*
        使用同步方式用三个线程来输出abcabcabcabcabc
        t1线程只能输出a,t2只能输出b，t3只能输出c
        最后结果输出长度为15的字符串，思路就是同步，t1运行完t2才能运行，t2运行完t3才能运行
        依此类推
*/

public class demo_01 {


    private static  int num = 0;
    private final  static Object obj = new Object();

    public  static void f(){
        Thread t1 = new Thread(()->{
            while(true){
                synchronized (obj){
                    obj.notifyAll();
                    if(num % 3 == 0) {
                        System.out.print("a");
                        num++;
                        break;
                    }


                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }




                }
            }
        },"t1");

        Thread t2 = new Thread(()->{
            while(true){
                synchronized (obj){
                    obj.notifyAll();
                    if(num % 3 == 1) {
                        System.out.print("b");
                        num++;
                        break;
                    }


                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        },"t2");

        Thread t3 = new Thread(()->{
            while(true){
                synchronized (obj){
                    obj.notifyAll();
                    if(num % 3 == 2) {
                        System.out.print("c");
                        num++;
                        break;
                    }


                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }



                }
            }
        },"t3");

        t1.start();
        t2.start();
        t3.start();
    }
    public static void main(String[] args) {

        for(int i = 0 ; i < 5;i++){
            f();
        }
//        try {
//            Thread.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }




}
