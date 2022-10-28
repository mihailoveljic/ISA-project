package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
    ApiKey findByBloodBankId(long bloodBankId);
    ApiKey findByApiKey(String apiKey);
}
