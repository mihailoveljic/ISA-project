package eu.dreamTeam.isabackend.rabbitmq.consumer;

import eu.dreamTeam.isabackend.rabbitmq.constants.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

//    @RabbitListener(queues = Constants.QUEUE)
//    public void consumeMessageFromQueue(String string) {
//        var a = string;
//        System.out.println("Message from rabitMq: " + string);
//    }

}
