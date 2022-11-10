package eu.dreamTeam.isabackend.rabbitmq.publisher;

import eu.dreamTeam.isabackend.rabbitmq.constants.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestPublisher {
    @Autowired
    private RabbitTemplate template;
    @PostMapping
    public String test(@RequestBody String test){
        template.convertAndSend(Constants.EXCHANGE, Constants.ROUTING_KEY, test);
        return "Message sent to rabbitMQ";
    }
}
