package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.handler.InvalidApiKeyException;
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
public class ApiKeyController{

    @Autowired
    private ApiKeyService apiKeyService;

    @GetMapping(value = "/validate")
    public ResponseEntity validateApiKey(HttpServletRequest httpServletRequest){
        var apiKey = httpServletRequest.getHeader("Authorization");
        if(apiKeyService.ValidateApiKey(apiKey)) return new ResponseEntity<>(HttpStatus.OK);
        throw new InvalidApiKeyException();

    }
}
