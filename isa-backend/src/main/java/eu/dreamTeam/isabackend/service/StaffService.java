package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.dto.UpdateStaffDTO;
import eu.dreamTeam.isabackend.model.Address;
import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.model.enums.Gender;
import eu.dreamTeam.isabackend.repository.StaffRepository;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Staff update(UpdateStaffDTO staff, String email) {
        Staff staffToUpdate = staffRepository.getStaffByAccount_Email(email);
        Address address = new Address();
        address.setNumber(staff.getNumber());
        address.setStreet(staff.getStreet());
        address.setCountry(staff.getCountry());
        address.setCity(staff.getCity());
        staffToUpdate.setAddress(address);
        staffToUpdate.setName(staff.getName());
        staffToUpdate.setSurname(staff.getSurname());
        staffToUpdate.setGender(Gender.valueOf(staff.getGender()));
        staffToUpdate.setProfession(staff.getProfession());
        staffToUpdate.setProfessionInfo(staff.getProfessionInfo());
        staffToUpdate.setPhoneNumber(staff.getPhoneNumber());
        return staffRepository.save(staffToUpdate);
    }
}
