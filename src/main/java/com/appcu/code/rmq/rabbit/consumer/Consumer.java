package com.appcu.code.rmq.rabbit.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appcu.code.rmq.dto.Data;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Consumer {

   @RabbitListener(queues = { "${app.queue.name}" })
   public void receive(@Payload final Data message) {
      log.info("Received message {}", message);
      makeSlow();
   }

   private void makeSlow() {
      try {
         Thread.sleep(5000);
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

}
