package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyService {
    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public boolean ValidateApiKey(String apiKey) {
        var apiKeyFromDB = apiKeyRepository.findByApiKeyCode(apiKey);
        if(apiKey.equals(apiKeyFromDB)) return true;
        return false;
    }

}
