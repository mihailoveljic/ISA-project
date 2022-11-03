package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
