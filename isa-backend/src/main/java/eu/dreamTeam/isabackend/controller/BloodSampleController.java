package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.model.BloodSample;
import eu.dreamTeam.isabackend.service.BloodSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/blood-sample")
@Validated
public class BloodSampleController {

    private final BloodSampleService bloodSampleService;
    @Autowired
    public BloodSampleController(BloodSampleService bloodSampleService) {
        this.bloodSampleService = bloodSampleService;
    }
    @GetMapping
    public ResponseEntity<String> getBloodSample(
            @RequestParam @Pattern(regexp = "^(A|B|AB|O)_(POSITIVE|NEGATIVE)$") String bloodType,
            @RequestParam @Pattern(regexp = "^([1-9]|[1-9][0-9]|[1-9][0-9][0-9])$") String amount){
        BloodSample bs = bloodSampleService.getBloodSample(bloodType);
        if(bs == null)
            return new ResponseEntity<>("Blood type not found", HttpStatus.NOT_FOUND);
        if(bs.getAmount() < Integer.parseInt(amount))
            return new ResponseEntity<>("Not enough blood for chosen blood type", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>("Blood type exists in chosen amount", HttpStatus.OK);
    }
}
