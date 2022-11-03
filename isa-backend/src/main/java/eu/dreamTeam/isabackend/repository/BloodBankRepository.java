package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {
    BloodBank getBloodBankById(Long id);
}
