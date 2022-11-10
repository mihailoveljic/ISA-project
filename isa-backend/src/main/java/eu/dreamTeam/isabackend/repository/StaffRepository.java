package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StaffRepository  extends JpaRepository<Staff, Long> {
    Staff getStaffById(Long id);
    @Query(value = "SELECT * FROM public.staff " +
            "INNER JOIN public.account ON account.id = staff.account_id " +
            "INNER JOIN public.address ON address.id = staff.address_id " +
            "WHERE account.email LIKE ?1", nativeQuery = true)
    Staff getStaffByEmail(String email);
    Staff getStaffByAccount_Email(String email);
}
