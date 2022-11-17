package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.BloodBank;
import eu.dreamTeam.isabackend.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {
    BloodBank getBloodBankById(Long id);

}
