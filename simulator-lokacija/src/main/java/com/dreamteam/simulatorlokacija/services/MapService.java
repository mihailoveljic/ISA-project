package com.dreamteam.simulatorlokacija.services;

import com.dreamteam.simulatorlokacija.models.LatLng;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapService {

    @Value("${openrouteservice.api-key}")
    private String apiKey;
    public List<LatLng> getDirections(String origin, String destination){
        var result = new ArrayList<LatLng>();
        try{
            RestTemplate restTemplate = new RestTemplate();

            //konstruiše se url za pozivanje api-ja
            String url = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=" + apiKey + "&start=" + origin + "&end=" + destination;

            //pozivanje api-ja
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);


            //parsiranje json odgovora i dobavljanje putanje
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody()).get("features").get(0).get("geometry").get("coordinates");
            for (final JsonNode objNode : jsonNode) {
                result.add(new LatLng(objNode.get(1).asDouble(), objNode.get(0).asDouble()));
            }
        }catch (Exception e){
            return null;
        }
        return result;
    }
}
