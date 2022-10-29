package eu.dreamTeam.isabackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/api-key")
public class ApiKeyController {

    @GetMapping(value = "/validate")
    @CrossOrigin(origins = "*")
    public ResponseEntity validateApiKey(HttpServletRequest httpServletRequest){
        var apiKey = httpServletRequest.getHeader("Authorization");
        if (!apiKey.equals("veljin_kljuc")){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
