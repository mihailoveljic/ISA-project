package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository  extends JpaRepository<Staff, Long> {
    Staff getStaffByAccount_Email(String Email);
}
