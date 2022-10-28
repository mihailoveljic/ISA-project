package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodBankRepository  extends JpaRepository<BloodBank, Long> {
}
