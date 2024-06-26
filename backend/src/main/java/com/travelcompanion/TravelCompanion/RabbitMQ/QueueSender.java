package com.travelcompanion.TravelCompanion.RabbitMQ;

import com.travelcompanion.TravelCompanion.model.User;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void send(User user) {
        rabbitTemplate.convertAndSend(this.queue.getName(), user);
    }
}