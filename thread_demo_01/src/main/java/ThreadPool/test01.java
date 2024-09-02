package ThreadPool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test01 {
    private int n;
    private volatile Boolean A = false;


    private static Object lock;
    private volatile int current = 0;
    ExecutorService executor = Executors.newFixedThreadPool(3);
    public test01(int n) {
        this.n = n;
        lock = new Object();

    }
    public void show1() {
        executor.execute(() -> {
            for (int i = 0 ; i < n; i += 2) {
            synchronized (lock) {
                    while ((current % 2) != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("0     " + current);
                    current = current + 1;
                    lock.notifyAll();
                }
            }
        });
    }

    public void show2() {
        executor.execute(() -> {
            for (int i = 0 ; i < n ; i += 4) {
            synchronized (lock) {
                    while (((current % 2) == 0) || !A) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    A = false;
                    System.out.println("2     " + current);
                    current = current + 1;
                    lock.notifyAll();
                }
            }
        });
    }

    public void show3() {
        executor.execute(() -> {
            for(int i = 0 ; i < n ; i += 4){
            synchronized (lock) {
                    while (((current % 2) == 0) || A) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    A = true;
                    System.out.println("1     " + current);
                    current = current + 1;
                    lock.notifyAll();
                }
            }
        });
    }


    public void show4(){
        Thread t1 = new Thread(()->{
            for(int i = 0 ; i < n ; i+=2) {
                synchronized (lock) {
                    while ((current % 2) != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("0     " + current);
                    current = current + 1;
                    lock.notifyAll();
                }
            }
        });

        Thread t2 = new Thread(()->{
            for(int i = 0 ; i < n; i +=  4) {
                synchronized (lock) {
                    while (((current % 2) == 0) || !A) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                        A = false;
                        System.out.println("2     " + current);
                        current = current + 1;
                        lock.notifyAll();

                }
            }
        });

        Thread t3 = new Thread(()->{
            for(int i = 0 ; i < n; i +=  4) {
                synchronized (lock) {
                    while (((current % 2) == 0) || A) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    A = true;
                    System.out.println("1     " + current);
                    current = current + 1;
                    lock.notifyAll();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
    public static void main(String[] args) {
        test01 temp = new test01(19784);
        temp.show4();
//        temp.show1();
//        temp.show2();
//        temp.show3();
    }

}
