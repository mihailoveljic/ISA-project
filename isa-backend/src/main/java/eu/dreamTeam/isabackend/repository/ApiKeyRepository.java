package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.ApiKey;
import eu.dreamTeam.isabackend.model.BloodSample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    ApiKey findByApiKeyCode(String apiKeyCode);
}
