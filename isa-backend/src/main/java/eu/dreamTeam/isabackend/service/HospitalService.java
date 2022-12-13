package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.model.Hospital;
import eu.dreamTeam.isabackend.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    public HospitalService(HospitalRepository hospitalRepository){
        this.hospitalRepository = hospitalRepository;
    }
    public Hospital create(Hospital hospital)
    {
        return hospitalRepository.save(hospital);
    }

     public List<Hospital> getAll(){
        return hospitalRepository.findAll();
    }
}
