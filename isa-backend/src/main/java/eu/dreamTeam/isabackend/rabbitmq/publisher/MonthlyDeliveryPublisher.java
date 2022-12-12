package eu.dreamTeam.isabackend.rabbitmq.publisher;

import eu.dreamTeam.isabackend.dto.MonthlyDeliveryDTO;
import eu.dreamTeam.isabackend.model.NewsForHospital;
import eu.dreamTeam.isabackend.rabbitmq.constants.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
@Service
public class MonthlyDeliveryPublisher {

    @Autowired
    private RabbitTemplate template;

    /*@Value("${myServerAddress}")
    private String serverAddress;*/

    public String send(@RequestBody MonthlyDeliveryDTO monthlyDeliveryDTO){ //
        template.convertAndSend(Constants.DELIVERY_EXCHANGE,Constants.DELIVERY_ROUTING_KEY, monthlyDeliveryDTO);
        return "Message sent to rabbitMQ";
    }
}
