package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.BloodSample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BloodSampleRepository extends JpaRepository<BloodSample, Long> {

    @Query(value = "select count(*) from blood_sample where blood_type like ?1", nativeQuery = true)
    int getBloodSample(String bloodType);

    List<BloodSample> getBloodSamplesByBloodBankId(Long id);

    @Query(value = "select sum(amount) from blood_sample where blood_type like ?1", nativeQuery = true)
    double getBloodSampleAmount(String bloodType);
}
