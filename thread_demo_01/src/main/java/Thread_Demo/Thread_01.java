package Thread_Demo;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j(topic = "c.Test")
public class Thread_01 {
    public static void main(String[] args) {
        AtomicInteger i =new AtomicInteger(0);

        System.out.println(i.get());

        System.out.println(i.getAndIncrement());//原子操作，直接1

        System.out.println(i.addAndGet(1));
        System.out.println(i.
                get());
        

    }
}
