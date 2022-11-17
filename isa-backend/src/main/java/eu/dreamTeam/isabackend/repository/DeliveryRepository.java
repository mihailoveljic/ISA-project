package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
