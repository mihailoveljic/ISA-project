package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.handler.InvalidApiKeyException;
import eu.dreamTeam.isabackend.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyService {
    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public boolean ValidateApiKey(String apiKey) {
        try {
            var apiKeyFromDB = apiKeyRepository.findByApiKeyCode(apiKey);
            return apiKey.equals(apiKeyFromDB.getApiKeyCode());
        }catch(Exception e){
            throw new InvalidApiKeyException();
        }
    }

}
