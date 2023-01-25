package com.dreamteam.simulatorlokacija.rabbitMQ.publishers;

import com.dreamteam.simulatorlokacija.models.LatLng;
import com.dreamteam.simulatorlokacija.rabbitMQ.constants.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class Publisher {
    @Autowired
    private RabbitTemplate template;

    @Async
    public void send(@RequestBody LatLng location){
        template.convertAndSend(Constants.EXCHANGE, Constants.ROUTING_KEY, location);
    }
}
