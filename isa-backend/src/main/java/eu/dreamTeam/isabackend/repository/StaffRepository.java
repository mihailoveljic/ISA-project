package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffRepository  extends JpaRepository<Staff, Long> {
    Staff getStaffById(Long id);
    @Query(value = "SELECT * FROM public.staff " +
            "INNER JOIN public.account ON account.id = staff.account_id " +
            "INNER JOIN public.address ON address.id = staff.address_id " +
            "WHERE account.email LIKE ?1", nativeQuery = true)
    Staff getStaffByEmail(String email);
    Staff getStaffByAccount_Email(String email);

    Staff getStaffByJmbg(String jmbg);
    @Query(value = "SELECT * FROM public.staff " +
            "WHERE staff.id != ?1 and staff.blood_bank_id = ?2", nativeQuery = true)
    List<Staff> getStaffFromSameCenter(Long staffId, Long bankId);
}
