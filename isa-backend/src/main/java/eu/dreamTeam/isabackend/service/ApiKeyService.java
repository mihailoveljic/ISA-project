package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.model.ApiKey;
import eu.dreamTeam.isabackend.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApiKeyService {
    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public List<ApiKey> getAllBloodBankApiKey(long id) {
        return apiKeyRepository.findAllByBloodBankId(id);
    }

    public boolean isValidApiKey(String apiKey, long bloodBankId){
        var apiKeyFromDataBase = getAllBloodBankApiKey(bloodBankId);

        boolean isValid = false;
        for (var key : apiKeyFromDataBase) {
            if(key.getApiKeyCode().equals(apiKey)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }

}
