package eu.dreamTeam.isabackend.rabbitmq.constants;

public class Constants {
    public static final String QUEUE = "news_queue";
    public static final String EXCHANGE = "news_exchange";
    public static final String ROUTING_KEY = "news_routing_key";

    public static final String REGISTER_QUEUE = "mothly_delivery_registration";
    public static final String REGISTER_EXCHANGE = "exchange";
    public static final String REGISTER_ROUTING_KEY = "register_routing_key";

    public static final String DELIVERY_QUEUE = "monthly_delivery";
    public static final String DELIVERY_EXCHANGE = "delivery_exchange";
    public static final String DELIVERY_ROUTING_KEY = "delivery_routing_key";
}
