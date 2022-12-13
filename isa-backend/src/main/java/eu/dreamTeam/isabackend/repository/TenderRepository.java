package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.tender.Tender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenderRepository extends JpaRepository<Tender, Integer> {
}
