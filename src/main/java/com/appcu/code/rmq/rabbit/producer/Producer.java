package com.appcu.code.rmq.rabbit.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@EnableRabbit
@RequiredArgsConstructor
public class Producer {

   private final RabbitTemplate rabbitTemplate;

   private final Queue queue;

   public void send(Object message) {
      rabbitTemplate.convertAndSend(queue.getName(), message);
   }

}
