package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.dto.SampleDTO;
import eu.dreamTeam.isabackend.handler.exceptions.StaffNotExistedException;
import eu.dreamTeam.isabackend.model.BloodSample;
import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.service.DeliveryLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/delivery-location")
public class DeliveryLocationController {

    @Autowired
    DeliveryLocationService deliveryLocationService;

    @GetMapping()
    public ResponseEntity<Object> getDeliveryLocation() {
        return new ResponseEntity<>(deliveryLocationService.getDeliveryLocation(), HttpStatus.OK);
    }


    @GetMapping("/begin")
    public ResponseEntity<Object> beginDelivery(@RequestParam String origin, @RequestParam String destination, @RequestParam double speed) {

        deliveryLocationService.setupDelivery(origin, destination);
        deliveryLocationService.beginDelivery(origin, destination, speed);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/end")
    public ResponseEntity<Object> endDelivery() {

        deliveryLocationService.endDelivery();

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
