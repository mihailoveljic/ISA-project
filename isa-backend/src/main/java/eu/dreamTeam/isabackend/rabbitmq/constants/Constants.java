package eu.dreamTeam.isabackend.rabbitmq.constants;

public class Constants {
    public static final String QUEUE = "news_queue";
    public static final String EXCHANGE = "news_exchange";
    public static final String ROUTING_KEY = "news_routing_key";


    public static final String LOCATION_QUEUE = "location_queue";
    public static final String LOCATION_EXCHANGE = "location_exchange";
    public static final String LOCATION_ROUTING_KEY = "location_routing_key";


    public static final String TENDERS_QUEUE = "tenders_queue";
    public static final String TENDERS_EXCHANGE = "tenders_exchange";
    public static final String TENDERS_ROUTING_KEY = "tenders_routing_key";
    public static final String TENDER_OFFER_CONFIRMATIONS_QUEUE = "tender_offer_confirmations_queue";
    public static final String TENDER_OFFER_CONFIRMATIONS_EXCHANGE = "tender_offer_confirmations_exchange";
    public static final String TENDER_OFFER_CONFIRMATIONS_ROUTING_KEY = "tender_offer_confirmations_routing_key";

    public static final String REGISTER_QUEUE = "mothly_delivery_registration";
    public static final String REGISTER_EXCHANGE = "exchange";
    public static final String REGISTER_ROUTING_KEY = "register_routing_key";

    public static final String DELIVERY_QUEUE = "monthly_delivery0";
    public static final String DELIVERY_EXCHANGE = "delivery_exchange";
    public static final String DELIVERY_ROUTING_KEY = "delivery_routing_key";
}
