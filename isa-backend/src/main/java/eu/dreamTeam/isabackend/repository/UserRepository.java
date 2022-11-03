package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
