package eu.dreamTeam.isabackend.repository;


import eu.dreamTeam.isabackend.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
