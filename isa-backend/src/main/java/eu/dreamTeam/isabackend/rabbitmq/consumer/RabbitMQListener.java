package eu.dreamTeam.isabackend.rabbitmq.consumer;

import eu.dreamTeam.isabackend.model.Hospital;
import eu.dreamTeam.isabackend.service.HospitalService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener implements MessageListener {

    private final HospitalService hospitalService;

    public RabbitMQListener(HospitalService hospitalService){
        this.hospitalService = hospitalService;
    }

    public void onMessage(Message message)
    {
        String[] parts = new String(message.getBody()).split(":");
        String hospitalName = parts[1].substring(1, parts[1].length() - 2);
        Hospital hospital = new Hospital();
        hospital.setHospitalName(hospitalName);
        hospitalService.create(hospital);
        System.out.println("Consuming Message - " + hospitalName);
    }
}
