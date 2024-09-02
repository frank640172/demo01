package com.example.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    //接受消息并进行处理
    @RabbitListener(queues =" ${mq.queue.name}")
    public  void process(String msg){
        //如果队列中有消息，则处理打印出来
        System.out.println("receiver:" + msg);

    }
}
