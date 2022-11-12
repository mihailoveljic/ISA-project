package eu.dreamTeam.isabackend.rabbitmq.publisher;

import eu.dreamTeam.isabackend.model.NewsForHospital;
import eu.dreamTeam.isabackend.rabbitmq.constants.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Publisher {
    @Autowired
    private RabbitTemplate template;

    @Value("${myServerAddress}")
    private String serverAddress;

    @PostMapping
    public String test(@RequestBody NewsForHospital newsForHospital){
        newsForHospital.setServerAddress(serverAddress);
        template.convertAndSend(Constants.EXCHANGE, Constants.ROUTING_KEY, newsForHospital);
        return "Message sent to rabbitMQ";
    }
}
