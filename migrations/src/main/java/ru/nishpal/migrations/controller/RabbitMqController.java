package ru.nishpal.migrations.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/v1/rabbitmq")
public class RabbitMqController {
    private final AmqpTemplate template;

    @Autowired
    public RabbitMqController(AmqpTemplate template) {
        this.template = template;
    }

    @PostMapping
    public ResponseEntity<String> emit(@RequestBody String message) {
        log.info("Emit to myQueue");

        template.convertAndSend("myQueue", message);

        return ResponseEntity.ok("Success emit to queue");
    }
}
