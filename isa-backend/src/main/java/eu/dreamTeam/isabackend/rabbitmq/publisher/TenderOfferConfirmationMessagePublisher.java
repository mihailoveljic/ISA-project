package eu.dreamTeam.isabackend.rabbitmq.publisher;

import eu.dreamTeam.isabackend.dto.message.TenderOfferConfirmationMessage;
import eu.dreamTeam.isabackend.model.NewsForHospital;
import eu.dreamTeam.isabackend.rabbitmq.constants.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TenderOfferConfirmationMessagePublisher {

    @Autowired
    private RabbitTemplate template;

    public String sendMessage(TenderOfferConfirmationMessage message){
        template.convertAndSend(Constants.TENDER_OFFER_CONFIRMATIONS_EXCHANGE, Constants.TENDER_OFFER_CONFIRMATIONS_ROUTING_KEY, message);
        return "Message sent to rabbitMQ";
    }
}
