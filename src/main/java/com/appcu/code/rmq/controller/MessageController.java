package com.appcu.code.rmq.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appcu.code.rmq.service.MessageService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/message")
@RestController
@RequiredArgsConstructor
public class MessageController {

   private final MessageService messageService;

   @GetMapping(value = "send")
   public void send() {
      final var randomId = ThreadLocalRandom.current().nextInt(1, 100 + 1);
      final var message = "Message #" + randomId;
      this.messageService.sendToRabbit(randomId, message);
   }

}
