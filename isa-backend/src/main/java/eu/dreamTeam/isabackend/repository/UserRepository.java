package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository  extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM public.users " +
            "INNER JOIN public.account ON account.id = users.account_id " +
            "INNER JOIN public.address ON address.id = users.address_id " +
            "WHERE account.email LIKE ?1", nativeQuery = true)
    User getUserByEmail(String email);
}
