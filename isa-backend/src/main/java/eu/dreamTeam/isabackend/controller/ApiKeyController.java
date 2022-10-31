package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.service.ApiKeyService;
import eu.dreamTeam.isabackend.service.BloodSampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/api-key")
public class ApiKeyController {

    private final ApiKeyService apiKeyService;
    @Autowired
    public ApiKeyController(BloodSampleService bloodSampleService, ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @GetMapping(value = "/validate/{bloodBankId}")
    public ResponseEntity validateApiKey(HttpServletRequest httpServletRequest, @PathVariable int bloodBankId){
        var apiKey = httpServletRequest.getHeader("Authorization");
        boolean isValid = apiKeyService.isValidApiKey(apiKey, bloodBankId);
        if(isValid) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }
}
