package com.appcu.code.rmq.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

   private final String host;

   private final Integer port;

   private final String username;

   private final String password;

   private final String queueName;

   public RabbitMQConfig(@Value("${spring.rabbitmq.host}") final String host, @Value("${spring.rabbitmq.port}") final Integer port,
         @Value("${spring.rabbitmq.username}") final String username, @Value("${spring.rabbitmq.password}") final String password,
         @Value("${spring.rabbitmq.queue.q1-name}") final String queueName) {
      this.host = host;
      this.port = port;
      this.username = username;
      this.password = password;
      this.queueName = queueName;
   }

   @Bean
   public ConnectionFactory connectionFactory() {
      CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
      connectionFactory.setHost(host);
      connectionFactory.setPort(port);
      connectionFactory.setUsername(username);
      connectionFactory.setPassword(password);
      return connectionFactory;
   }

   @Bean
   public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(final ConnectionFactory connectionFactory) {
      SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
      factory.setConnectionFactory(connectionFactory);
      factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
      return factory;
   }

   @Bean
   public Queue queue() {
      return new Queue(queueName, true);
   }

}
