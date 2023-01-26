package eu.dreamTeam.isabackend.rabbitmq.consumer;

import eu.dreamTeam.isabackend.model.DeliveryLocation;
import eu.dreamTeam.isabackend.model.Hospital;
import eu.dreamTeam.isabackend.repository.DeliveryLocationRepository;
import eu.dreamTeam.isabackend.service.DeliveryLocationService;
import eu.dreamTeam.isabackend.service.HospitalService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LocationListener implements MessageListener {


    private DeliveryLocationService deliveryLocationService;

    public LocationListener(DeliveryLocationService deliveryLocationService){
        this.deliveryLocationService = deliveryLocationService;
    }

    public void onMessage(Message message)
    {
        var delivery = deliveryLocationService.findById(1);
        if(delivery == null) return;


        String[] parts = (message.toString().split(":"));
        String latitude = parts[2].split(",")[0];
        String longitude = parts[3].split("}")[0];


        delivery.setLatitude(Double.parseDouble(latitude));
        delivery.setLongitude(Double.parseDouble(longitude));

        if(delivery.getEndLatitude() == delivery.getLatitude() && delivery.getEndLongitude() == delivery.getLongitude()){
            delivery.setDelivered(true);
        }

        deliveryLocationService.save(delivery);
    }
}
