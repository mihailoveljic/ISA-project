package com.dreamteam.simulatorlokacija.controllers;

import com.dreamteam.simulatorlokacija.models.LatLng;
import com.dreamteam.simulatorlokacija.rabbitMQ.publishers.Publisher;
import com.dreamteam.simulatorlokacija.services.MapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/delivery")
public class DeliveryController {

    @Autowired
    private MapService mapService;
    @Autowired
    private Publisher publisher;

    @CrossOrigin
    @GetMapping("/begin")
    public ResponseEntity<Object> simulate(@RequestParam String origin, @RequestParam String destination, @RequestParam double speed) throws InterruptedException {

        //koristi OpenStreetMap API da dobije putanju između tačke A i tačke B
        List<LatLng> path = mapService.getDirections(origin, destination);

        sendLocation(path, speed);

        return ResponseEntity.ok("success");
    }

    @Async
    private void sendLocation(List<LatLng> path, double speed){
        try{
            //za svaku koordinatu u putanji
            LatLng currentCoordinate = null;
            for (int i = 0; i < path.size(); i++) {
                currentCoordinate = path.get(i);
                log.info(currentCoordinate.toString());
                Thread.sleep((long) (speed * 1000));
                publisher.send(currentCoordinate);
            }
        }catch (Exception e){
            log.info(e.getMessage());
        }

    }


}
