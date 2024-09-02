package ThreadPool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*实现线程池*/
public class demo_01 {


    public static void main(String[] args) {

    }

    public static class BlockQueue<T> {
        private int capacity; //阻塞队列的容量

        ReentrantLock lock = new ReentrantLock();
        //分别存放两个条件变量来实现线程安全
        Condition QueueEmpty = lock.newCondition();
        Condition QueueFull = lock.newCondition();

        //阻塞队列
        Deque<T> queue ;


        public BlockQueue(int capacity) {
            this.capacity = capacity;
            queue = new ArrayDeque<>();
        }

        //从队列里取
        T take() {
            synchronized (this.queue) {
                while (queue.size() == 0) {
                    try {
                        QueueEmpty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                T task = queue.removeFirst();
                QueueFull.signalAll();
                return task;
            }

        }

        //往队列里存
        void put(T task) {
            synchronized (this.queue) {
                while (queue.size() == capacity) {//阻塞等待
                    try {
                        QueueFull.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                queue.addLast(task);
                //如果执行到这里，说明你肯定存了东西了
                QueueEmpty.signalAll();
            }
        }
    }




    public class  ThreadPool{

           private  BlockQueue<Runnable> q;

           //存放的是线程
           private HashSet<Worker> workers = new HashSet<>();

           //存放线程最多的个数
            private int coreSize;

        public ThreadPool(BlockQueue<Runnable> q, int coreSize,int capacity) {
            this.q = new BlockQueue<Runnable>(capacity);
            this.coreSize = coreSize;
        }

        public void execute(Runnable task){
             if(workers.size() < coreSize){
                 Worker worker = new Worker(task);
                 workers.add(worker);
                 worker.start();
             }else{
                 //已经满了
                 q.put(task);
             }
        }

        public class Worker extends Thread{

            private  Runnable task;

            public Worker(Runnable task) {
                this.task = task;
            }

            @Override
            public void run() {
                while(task != null || q.capacity > 0 ){
//                    try{
//
//                    }catch (){
//
//                    }
                }
            }
        }


    }



}
