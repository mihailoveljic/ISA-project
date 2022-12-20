package eu.dreamTeam.isabackend.rabbitmq.publisher;

import eu.dreamTeam.isabackend.dto.BloodBankDTO;
import eu.dreamTeam.isabackend.dto.BloodSampleDTO;
import eu.dreamTeam.isabackend.dto.MonthlyDeliveryDTO;
import eu.dreamTeam.isabackend.dto.SampleDTO;
import eu.dreamTeam.isabackend.model.BloodSample;
import eu.dreamTeam.isabackend.model.Hospital;
import eu.dreamTeam.isabackend.service.BloodSampleService;
import eu.dreamTeam.isabackend.service.HospitalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
@EnableScheduling
public class ScheduledDelivery {
    private final MonthlyDeliveryPublisher monthlyDeliveryPublisher;
    private final HospitalService hospitalService;
    private final BloodSampleService bloodSampleService;

    @Autowired
    public ScheduledDelivery(MonthlyDeliveryPublisher monthlyDeliveryPublisher,
                             HospitalService hospitalService, BloodSampleService bloodSampleService) {
        this.monthlyDeliveryPublisher = monthlyDeliveryPublisher;
        this.hospitalService = hospitalService;
        this.bloodSampleService = bloodSampleService;
    }

    @Scheduled(fixedRate = 60*3000)
    public void sendMessage() {
       /* List<Hospital> hospitals = hospitalService.getAll();
        List<BloodSample> samples = bloodSampleService.getAll();
        List<BloodSampleDTO> dtos = new ArrayList<BloodSampleDTO>() ;
        String message = "Nabavka uspesno obavljena";
        if(hospitals.isEmpty())
            return;
        for(BloodSample bs : samples){
            BloodSampleDTO bsDTO = new BloodSampleDTO();
            bsDTO.setAmount(100);
            bsDTO.setBloodType(bs.getBloodType());
            if(!bloodSampleService.getBloodSampleForPurchase(bs.getBloodType().toString(), 100))
            {
                bsDTO.setAmount(0);
                message = "Nije bilo moguce isporuciti sve tipove krvi";
            }
            dtos.add(bsDTO);
        }
        MonthlyDeliveryDTO mdDTO = new MonthlyDeliveryDTO();
        mdDTO.setMessage(message);
        mdDTO.setBloodSamples(dtos);
        monthlyDeliveryPublisher.send(mdDTO); //
        System.out.println("Sent :" + message.toString());*/
    }
}
