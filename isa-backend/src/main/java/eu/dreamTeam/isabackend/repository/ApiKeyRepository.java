package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.ApiKey;
import eu.dreamTeam.isabackend.model.BloodSample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
    @Query(value = "select * from api_key where is_valid = true", nativeQuery = true)
    ApiKey getValidApiKey();
}
