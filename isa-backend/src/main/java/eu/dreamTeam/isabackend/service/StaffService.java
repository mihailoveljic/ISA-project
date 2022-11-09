package eu.dreamTeam.isabackend.service;


import eu.dreamTeam.isabackend.dto.StaffDTO;
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

    public Staff update(StaffDTO staff) {
        Staff staffToUpdate = staffRepository.getStaffById(staff.getId());
        staffToUpdate.getAddress().setNumber(staff.getNumber());
        staffToUpdate.getAddress().setStreet(staff.getStreet());
        staffToUpdate.getAddress().setCountry(staff.getCountry());
        staffToUpdate.getAddress().setCity(staff.getCity());
        staffToUpdate.setName(staff.getName());
        staffToUpdate.setSurname(staff.getSurname());
        staffToUpdate.setGender(Gender.valueOf(staff.getGender()));
        staffToUpdate.setProfession(staff.getProfession());
        staffToUpdate.setProfessionInfo(staff.getProfessionInfo());
        staffToUpdate.setPhoneNumber(staff.getPhoneNumber());
        return staffRepository.save(staffToUpdate);
    }

    public Staff getByEmail(String email) {
        return staffRepository.getStaffByEmail(email);
    }
}
