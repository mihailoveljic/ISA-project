package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.model.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemAdminRepository  extends JpaRepository<SystemAdmin, Long> {

    SystemAdmin getSystemAdminByJmbg(String jmbg);
}
