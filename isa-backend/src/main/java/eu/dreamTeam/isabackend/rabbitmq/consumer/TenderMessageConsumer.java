package eu.dreamTeam.isabackend.rabbitmq.consumer;

import eu.dreamTeam.isabackend.dto.message.TenderMessage;
import eu.dreamTeam.isabackend.rabbitmq.constants.Constants;
import eu.dreamTeam.isabackend.service.TenderMessageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TenderMessageConsumer {

    @Autowired
    TenderMessageService tenderMessageService;

    @RabbitListener(queues = Constants.TENDERS_QUEUE)
    public void listenForTenderMessages(TenderMessage tenderMessage) {
        tenderMessageService.save(tenderMessage);
    }
}
