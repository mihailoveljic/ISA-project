package eu.dreamTeam.isabackend.service;


import eu.dreamTeam.isabackend.dto.StaffDTO;
import eu.dreamTeam.isabackend.dto.StaffWithCenterDTO;
import eu.dreamTeam.isabackend.model.Account;
import eu.dreamTeam.isabackend.model.Address;
import eu.dreamTeam.isabackend.model.BloodBank;
import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.model.enums.Gender;
import eu.dreamTeam.isabackend.repository.AccountRepository;
import eu.dreamTeam.isabackend.repository.AddressRepository;
import eu.dreamTeam.isabackend.repository.BloodBankRepository;
import eu.dreamTeam.isabackend.repository.StaffRepository;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    private final StaffRepository staffRepository;
    private final AddressRepository addressRepository;
    private final AccountRepository accountRepository;
    private final BloodBankRepository bloodBankRepository;

    public StaffService(StaffRepository staffRepository, AddressRepository addressRepository,
                        AccountRepository accountRepository, BloodBankRepository bloodBankRepository) {
        this.staffRepository = staffRepository;
        this.addressRepository = addressRepository;
        this.accountRepository = accountRepository;
        this.bloodBankRepository = bloodBankRepository;
    }

    public Staff update(StaffDTO staff) {
        Staff staffToUpdate = staffRepository.getStaffById(staff.getId());
        staffToUpdate.getAddress().setNumber(staff.getNumber());
        staffToUpdate.getAddress().setStreet(staff.getStreet());
        staffToUpdate.getAddress().setCountry(staff.getCountry());
        staffToUpdate.getAddress().setCity(staff.getCity());
        staffToUpdate.setName(staff.getName());
        staffToUpdate.setSurname(staff.getSurname());
        staffToUpdate.setProfession(staff.getProfession());
        staffToUpdate.setProfessionInfo(staff.getProfessionInfo());
        staffToUpdate.setPhoneNumber(staff.getPhoneNumber());
        return staffRepository.save(staffToUpdate);
    }

    public Staff create(StaffWithCenterDTO staff) {
        Staff new_staff = new Staff();
        Address address = new Address();
        address.setNumber(staff.getNumber());
        address.setStreet(staff.getStreet());
        address.setCountry(staff.getCountry());
        address.setCity(staff.getCity());
        address = addressRepository.save(address);
        new_staff.setAddress(address);
        Account account = new Account();
        account.setEmail(staff.getEmail());
        account.setPassword("asdasd"); //TODO
        account.setRequiredPasswordChange(false);
        account = accountRepository.save(account);
        new_staff.setAccount(account);
        new_staff.setName(staff.getName());
        new_staff.setSurname(staff.getSurname());
        new_staff.setProfession(staff.getProfession());
        new_staff.setProfessionInfo(staff.getProfessionInfo());
        new_staff.setPhoneNumber(staff.getPhoneNumber());
        if (staff.getGender().equals("MALE"))
            new_staff.setGender(Gender.MALE);
        else
            new_staff.setGender(Gender.FEMALE);
        new_staff.setJmbg(staff.getJmbg());
        Long bbId = staff.getBloodBankId();
        BloodBank bb = bloodBankRepository.getBloodBankById(bbId);
        if (bb != null)
            new_staff.setBloodBank(bb);
        return staffRepository.save(new_staff);
    }

    public boolean checkStaffByJmbg(String jmbg) {return staffRepository.getStaffByJmbg(jmbg) != null;}

    public Staff getByEmail(String email) {
        return staffRepository.getStaffByEmail(email);
    }
}
