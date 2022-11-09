package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.BloodBank;
import eu.dreamTeam.isabackend.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {
    BloodBank getBloodBankById(Long id);

    @Query(value = "SELECT * FROM public.blood_bank " +
            "INNER JOIN public.staff ON staff.blood_bank_id = blood_bank.id " +
            "INNER JOIN public.account ON account.id = staff.account_id " +
            "INNER JOIN public.address ON address.id = blood_bank.address_id " +
            "WHERE account.email LIKE ?1", nativeQuery = true)
    BloodBank getBloodBankByStaffEmail(String email);
}
