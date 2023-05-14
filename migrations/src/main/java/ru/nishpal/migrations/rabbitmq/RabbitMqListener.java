package ru.nishpal.migrations.rabbitmq;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@EnableRabbit
public class RabbitMqListener {

    @RabbitListener(queues = "myQueue")
    public void processMyQueue(String message) {
        log.info("Received from myQueue: {}", message);
    }
}