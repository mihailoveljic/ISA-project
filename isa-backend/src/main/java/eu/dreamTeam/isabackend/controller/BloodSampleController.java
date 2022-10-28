package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.model.ApiKey;
import eu.dreamTeam.isabackend.model.BloodBank;
import eu.dreamTeam.isabackend.model.BloodSample;
import eu.dreamTeam.isabackend.service.ApiKeyService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/blood-sample")
@Validated
public class BloodSampleController {

    private final ApiKeyService apiKeyService;
    private final BloodSampleService bloodSampleService;
    @Autowired
    public BloodSampleController(BloodSampleService bloodSampleService, ApiKeyService apiKeyService) {
        this.bloodSampleService = bloodSampleService;
        this.apiKeyService = apiKeyService;
    }
    @GetMapping
    public ResponseEntity<String> getBloodSample(
            HttpServletRequest servletRequest
            //@RequestParam @Pattern(regexp = "^(A|B|AB|O)_(POSITIVE|NEGATIVE)$") String bloodType,
            //@RequestParam @Pattern(regexp = "^([1-9]|[1-9][0-9]|[1-9][0-9][0-9])$") String amount)
    )
            {
        try {
            var apiKey = servletRequest.getHeader("Authorization");
            var parts = apiKey.split(" ");
            ApiKey apiKeyFromDb = this.apiKeyService.GetByApiKey(parts[1]);
            if(apiKeyFromDb == null) throw new Exception("Bad api-key!");

            BloodSample bs = bloodSampleService.getBloodSample("0_NEGATIVE");
            if(bs == null)
                return new ResponseEntity<>("Blood type not found", HttpStatus.NOT_FOUND);
            if(bs.getAmount() < Integer.parseInt("100"))
                return new ResponseEntity<>("Not enough blood for chosen blood type", HttpStatus.NOT_FOUND);

            return new ResponseEntity<>("Blood type exists in chosen amount", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Api-Key is not valid!", HttpStatus.FORBIDDEN);
        }

    }
}
