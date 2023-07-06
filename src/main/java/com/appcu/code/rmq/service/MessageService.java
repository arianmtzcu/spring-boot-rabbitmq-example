package com.appcu.code.rmq.service;

import org.springframework.stereotype.Service;

import com.appcu.code.rmq.rabbit.producer.Producer;
import com.appcu.code.rmq.dto.Data;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

   private final Producer producer;

   public void sendToRabbit(final int id, final String message) {
      final var data = new Data(id, message);
      log.info("Message '{}' will be send ... ", data);
      this.producer.send(data);
   }

}
