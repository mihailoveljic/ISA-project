package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository  extends JpaRepository<Address, Long> {
    Address getAddressById(Long id);
}
