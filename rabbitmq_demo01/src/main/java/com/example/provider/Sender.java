package com.example.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Sender {

  @Autowired
  private AmqpTemplate template;

  @Value("${mq.queue.name}")
  private  String  queueName;

  public  void send(String msg){
      template.convertAndSend(queueName,msg);

  }

}
