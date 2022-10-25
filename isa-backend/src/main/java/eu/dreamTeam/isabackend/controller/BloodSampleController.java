package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.model.BloodSample;
import eu.dreamTeam.isabackend.service.BloodSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bloodsample")
public class BloodSampleController {

    private final BloodSampleService bloodSampleService;
    @Autowired
    public BloodSampleController(BloodSampleService bloodSampleService) {
        this.bloodSampleService = bloodSampleService;
    }
    @GetMapping
    public ResponseEntity<String> getAll(
            @RequestParam(required = true) String bloodType,
            @RequestParam(defaultValue = "0") double amount){
        BloodSample bs = bloodSampleService.getBloodSample(bloodType);
        if(bs == null)
            return new ResponseEntity<>("Blood type not found", HttpStatus.NOT_FOUND);
        if(amount == 0)
            return new ResponseEntity<>("Blood type exists", HttpStatus.OK);
        if(bs.getAmount() < amount)
            return new ResponseEntity<>("Not enough blood for chosen blood type", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>("Blood type exists in chosen amount", HttpStatus.OK);
    }
}
