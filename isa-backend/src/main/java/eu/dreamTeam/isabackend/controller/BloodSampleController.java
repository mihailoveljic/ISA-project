package eu.dreamTeam.isabackend.controller;
import eu.dreamTeam.isabackend.handler.InvalidApiKeyException;
import eu.dreamTeam.isabackend.model.BloodSample;
import eu.dreamTeam.isabackend.service.ApiKeyService;
import eu.dreamTeam.isabackend.service.BloodSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("{bloodBankId}")
    public ResponseEntity<String> getBloodSample(
            HttpServletRequest httpServletRequest,
            @PathVariable int bloodBankId,
            @RequestParam @Pattern(regexp = "^(A|B|AB|O)_(POSITIVE|NEGATIVE)$") String bloodType,
            @RequestParam @Pattern(regexp = "^([1-9]|[1-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9][0-9][0-9])$") String amount
    )
        {
            var apiKey = httpServletRequest.getHeader("Authorization");
            if (!apiKeyService.isValidApiKey(apiKey, bloodBankId)){
                throw new InvalidApiKeyException();
            }
            BloodSample bs = bloodSampleService.getBloodSample(bloodType);
            if(bs == null)
                return new ResponseEntity<>("Blood type not found", HttpStatus.NOT_FOUND);
            if(bs.getAmount() < Integer.parseInt(amount))
                return new ResponseEntity<>("Not enough blood for chosen blood type", HttpStatus.NOT_FOUND);

            return new ResponseEntity<>("Blood type exists in chosen amount", HttpStatus.OK);
        }
}
