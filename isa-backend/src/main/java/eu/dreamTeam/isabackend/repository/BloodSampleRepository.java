package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.BloodSample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BloodSampleRepository extends JpaRepository<BloodSample, Long> {

    @Query(value = "select * from blood_sample where blood_type like ?1", nativeQuery = true)
    BloodSample getBloodSample(String bloodType);
}
