package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.tender.TenderOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenderOfferRepository extends JpaRepository<TenderOffer, Integer> {
}
