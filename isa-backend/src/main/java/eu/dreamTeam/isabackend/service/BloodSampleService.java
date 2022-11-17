package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.model.BloodSample;
import eu.dreamTeam.isabackend.repository.BloodSampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class BloodSampleService {

    private final BloodSampleRepository bloodSampleRepository;
    public BloodSampleService(BloodSampleRepository bloodSampleRepository) {
        this.bloodSampleRepository = bloodSampleRepository;
    }

    public int getBloodSample(String bloodType){
        return bloodSampleRepository.getBloodSample(bloodType);
    }

    public double getBloodSampleAmount(String bloodType){
        return bloodSampleRepository.getBloodSampleAmount(bloodType);
    }

    public List<BloodSample> getBloodSamplesForCenter(Long centerId){
        return bloodSampleRepository.getBloodSamplesByBloodBankId(centerId);
    }

}
