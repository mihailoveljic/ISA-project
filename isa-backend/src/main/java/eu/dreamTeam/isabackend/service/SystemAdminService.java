package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.dto.AdminDTO;
import eu.dreamTeam.isabackend.model.Account;
import eu.dreamTeam.isabackend.model.Address;
import eu.dreamTeam.isabackend.model.SystemAdmin;
import eu.dreamTeam.isabackend.model.enums.Gender;
import eu.dreamTeam.isabackend.repository.AccountRepository;
import eu.dreamTeam.isabackend.repository.AddressRepository;
import eu.dreamTeam.isabackend.repository.SystemAdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SystemAdminService {
    private final SystemAdminRepository systemAdminRepository;
    private final AddressRepository addressRepository;
    private final AccountRepository accountRepository;

    public  SystemAdminService(SystemAdminRepository systemAdminRepository, AddressRepository addressRepository,
                               AccountRepository accountRepository){
        this.systemAdminRepository = systemAdminRepository;
        this.addressRepository = addressRepository;
        this.accountRepository = accountRepository;
    }

    public List<SystemAdmin> getAllAdmins(){
        return systemAdminRepository.findAll();
    }

    public SystemAdmin create(AdminDTO admin){
        SystemAdmin new_admin = new SystemAdmin();
        Address address = new Address();
        address.setNumber(admin.getNumber());
        address.setStreet(admin.getStreet());
        address.setCountry(admin.getCountry());
        address.setCity(admin.getCity());
        address = addressRepository.save(address);

        new_admin.setAddress(address);
        Account account = new Account();
        account.setEmail(admin.getEmail());
        UUID uuid = UUID.randomUUID();
        account.setPassword(uuid.toString());
        account.setRequiredPasswordChange(true);
        account = accountRepository.save(account);

        new_admin.setAccount(account);
        new_admin.setName(admin.getName());
        new_admin.setSurname(admin.getSurname());
        new_admin.setProfession(admin.getProfession());
        new_admin.setProfessionInfo(admin.getProfessionInfo());
        new_admin.setPhoneNumber(admin.getPhoneNumber());
        new_admin.setJmbg(admin.getJmbg());
        if (admin.getGender().equals("MALE"))
            new_admin.setGender(Gender.MALE);
        else
            new_admin.setGender(Gender.FEMALE);
        return systemAdminRepository.save(new_admin);
    }

    public boolean checkAdminByJmbg(String jmbg) {return systemAdminRepository.getSystemAdminByJmbg(jmbg) != null;}
}
