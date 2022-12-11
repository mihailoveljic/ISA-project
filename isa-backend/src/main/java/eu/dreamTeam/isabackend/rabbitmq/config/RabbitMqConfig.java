package eu.dreamTeam.isabackend.rabbitmq.config;

import eu.dreamTeam.isabackend.rabbitmq.constants.Constants;
import eu.dreamTeam.isabackend.rabbitmq.consumer.RabbitMQListener;
import eu.dreamTeam.isabackend.service.HospitalService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    private final HospitalService hospitalService;

    public RabbitMqConfig(HospitalService hospitalService){
        this.hospitalService = hospitalService;
    }

    /*@Bean
    public Queue queue(){
        return new Queue(Constants.QUEUE);
    }*/
    @Bean
    public Queue register_queue(){
        return new Queue(Constants.REGISTER_QUEUE);
    }
    @Bean
    public Queue delivery_queue(){
        return new Queue(Constants.DELIVERY_QUEUE);
    }

    /*@Bean
    public TopicExchange exchange(){
        return new TopicExchange(Constants.EXCHANGE);
    }*/
    @Bean
    public FanoutExchange delivery_exchange() {
        return new FanoutExchange(Constants.DELIVERY_EXCHANGE);
    }
    @Bean
    public FanoutExchange register_exchange() {
        return new FanoutExchange(Constants.REGISTER_EXCHANGE);
    }
    /*@Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(Constants.ROUTING_KEY);
    }*/
   @Bean
    public Binding register_binding(){
        return BindingBuilder.bind(register_queue()).to(register_exchange());
    }
    @Bean
    public Binding delivery_binding(){
        return BindingBuilder.bind(delivery_queue()).to(delivery_exchange());
    }
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory1 ) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory1);
        simpleMessageListenerContainer.setQueues(register_queue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQListener(hospitalService));
        return simpleMessageListenerContainer;

    }
}
