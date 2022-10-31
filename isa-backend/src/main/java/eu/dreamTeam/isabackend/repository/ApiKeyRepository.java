package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.ApiKey;
import eu.dreamTeam.isabackend.model.BloodSample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
    @Query(value = "select * from api_key where blood_bank_id = ?1", nativeQuery = true)
    List<ApiKey> findAllByBloodBankId(long id);
}
