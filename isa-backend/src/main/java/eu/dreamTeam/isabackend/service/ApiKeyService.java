package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.model.ApiKey;
import eu.dreamTeam.isabackend.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApiKeyService {
    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public ApiKey getBloodBankApiKey() {
        return apiKeyRepository.getValidApiKey();
    }

}
