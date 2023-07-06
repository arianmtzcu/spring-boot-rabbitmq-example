# Spring Boot RabbitMQ Example
This project is a Spring Boot application that demonstrates how to send and receive messages using RabbitMQ. It includes a REST controller for sending messages, a producer for publishing messages to a RabbitMQ queue, and a consumer for receiving and processing messages from the queue.

## Features
- **REST API:** Expose endpoints to send messages to RabbitMQ.
- **Message Producer:** Publishes messages to a RabbitMQ queue.
- **Message Consumer:** Listens for messages on the RabbitMQ queue and logs them to the console.
- **Lombok:** Simplifies Java code by generating getters, setters, and other methods automatically.

## Dependencies
The project relies on the following dependencies:
- **Spring Boot Starter Web:** Provides the web framework to build REST APIs.
- **Spring Boot Starter AMQP:** Simplifies the use of AMQP (Advanced Message Queuing Protocol) in Spring applications.
- **Lombok:** Reduces boilerplate code in Java.
- **Spring Boot Starter Test:** Supports testing with Spring Boot.
- **Spring Rabbit Test:** Provides tools for testing RabbitMQ interactions.

## Application Configuration
The application is configured to connect to a RabbitMQ instance using the `application.yml` file. You can customize the RabbitMQ connection properties such as host, port, username, and password.

```yaml
spring:
  rabbitmq:
    host: 127.0.0.1
    port: 5672
    username: <username>
    password: <password>
    queue:
      q1-name: mq-queue-1
```
Ensure that RabbitMQ is running and accessible with the credentials provided.

## Running the Application

1. **Clone the Repository:**
```shell
   git clone https://github.com/arianmtzcu/spring-boot-rabbitmq-example.git
   cd spring-boot-rabbitmq-example
```

2. **Build the Application:**
<br/> Use Maven to build the project:
```shell
   mvn clean install
```

3. **Run the Application:**
<br/> Start the application using the Spring Boot Maven plugin:
```shell
   mvn spring-boot:run
```

4. **Send a Message:**
<br/> You can send a message to RabbitMQ by making an HTTP POST request to the /send endpoint of the MessageController.
```shell
   curl -X POST http://localhost:8092/send -d "Your message here"
```

5. **View Received Messages**
<br/> The Consumer class logs the received messages to the console. Check the application logs to see the output.

## Sending Objects to RabbitMQ with acknowledge mode "MANUAL"
In this project, you can send complex objects as messages to RabbitMQ, and the acknowledgment of message processing is handled manually using AcknowledgeMode=MANUAL.

## What is AcknowledgeMode=MANUAL?
In RabbitMQ, when a message is delivered to a consumer, it needs to be acknowledged by the consumer to inform RabbitMQ that the message has been successfully processed. This acknowledgment prevents RabbitMQ from re-delivering the message and allows it to be removed from the queue.
There are different acknowledgment modes in RabbitMQ, and AcknowledgeMode=MANUAL gives the consumer explicit control over when and how the acknowledgment is sent.
AcknowledgeMode=MANUAL means that the consumer will manually acknowledge the message after it has been fully processed. This is useful in scenarios where:
- **Message processing is complex or involves multiple steps.** The consumer might want to ensure that all processing is complete and successful before sending an acknowledgment. This way, if any step fails, the message can be retried.
- **You want to handle errors gracefully.** If an error occurs during processing, the consumer can decide not to acknowledge the message, causing RabbitMQ to requeue it for another attempt or handle it in a different manner (e.g., send it to a dead-letter exchange).
- **You need greater control over message re-delivery.** By manually controlling when messages are acknowledged, you can implement custom logic to retry or move messages based on the type or frequency of errors.

## Contributions
This project is open to contributions. If you'd like to collaborate, please open an issue or send a pull request.

## License
This project is licensed under the MIT License. See the [LICENSE](https://opensource.org/license/MIT) file for more details.