package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.model.DeliveryLocation;
import eu.dreamTeam.isabackend.repository.DeliveryLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeliveryLocationService {

    @Autowired
    private DeliveryLocationRepository deliveryLocationRepository;


    public void save(DeliveryLocation deliveryLocation){
        deliveryLocationRepository.save(deliveryLocation);
    }

    public DeliveryLocation getDeliveryLocation(){
        return deliveryLocationRepository.findById(1L).orElse(null);
    }

    @Async
    public void beginDelivery(String origin, String destination, double speed){
        RestTemplate restTemplate = new RestTemplate();

        //konstruiše se url za pozivanje api-ja
        String url = "http://localhost:8888/api/delivery/begin?origin=" + origin + "&destination=" + destination + "&speed=" + speed;

        //pozivanje api-ja
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    }

    public void setupDelivery(String origin, String destination){
        var startLngLat = origin.split(",");
        var endLngLat = destination.split(",");

        var delivery = DeliveryLocation.builder()
                                                        .id(1)
                                                        .latitude(Double.parseDouble(startLngLat[1]))
                                                        .longitude(Double.parseDouble(startLngLat[0]))
                                                        .startLatitude(Double.parseDouble(startLngLat[1]))
                                                        .startLongitude(Double.parseDouble(startLngLat[0]))
                                                        .endLatitude(Double.parseDouble(endLngLat[1]))
                                                        .endLongitude(Double.parseDouble(endLngLat[0]))
                                                        .delivered(false)
                                                    .build();
       deliveryLocationRepository.save(delivery);
    }

    public DeliveryLocation findById(long id){
       return deliveryLocationRepository.findById(id).orElse(null);
    }

    public void endDelivery() {
        var delivery = deliveryLocationRepository.findById(1L).orElse(null);
        if(delivery == null) return;
        delivery.setDelivered(true);
        deliveryLocationRepository.save(delivery);
    }
}
