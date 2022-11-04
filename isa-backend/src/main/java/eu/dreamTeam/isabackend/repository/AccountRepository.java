package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account getAccountById(Long id);

    Account getAccountByEmail(String email);
}
