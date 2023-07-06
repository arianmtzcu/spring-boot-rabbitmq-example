package com.appcu.code.rmq.rabbit.consumer;

import static org.springframework.amqp.support.AmqpHeaders.DELIVERY_TAG;

import java.io.IOException;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appcu.code.rmq.dto.Data;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Consumer {

   private static final String ID = "id";

   @RabbitListener(queues = { "${spring.rabbitmq.queue.q1-name}" })
   public void receive(@Headers final Map<String, Object> headers, @Payload final Data message, Channel channel) throws IOException {
      final var tag = (Long) headers.get(DELIVERY_TAG);
      final var id = headers.get(ID);
      try {
         // Access to headers
         log.info("Received headers {}", headers);
         log.info("Received headers :: id={}, deliveryTag={}", id, tag);

         log.info("Received message {}", message);

         // Process message ...
         makeSlow();

         // Send to ACK manually to message-broker
         channel.basicAck(tag, false);
      } catch (Exception e) {
         // Handles errors and performs selective reject if necessary
         channel.basicReject(tag, false);
      }
   }

   private void makeSlow() {
      try {
         Thread.sleep(3000);
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

}
