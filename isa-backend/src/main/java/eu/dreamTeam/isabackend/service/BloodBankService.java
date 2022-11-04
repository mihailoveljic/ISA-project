package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.dto.UpdateBloodBankDTO;
import eu.dreamTeam.isabackend.model.Address;
import eu.dreamTeam.isabackend.model.BloodBank;
import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.model.WorkTime;
import eu.dreamTeam.isabackend.repository.AddressRepository;
import eu.dreamTeam.isabackend.repository.BloodBankRepository;
import eu.dreamTeam.isabackend.repository.StaffRepository;
import eu.dreamTeam.isabackend.repository.WorkTimeRepository;
import org.springframework.stereotype.Service;

@Service
public class BloodBankService {

    private final BloodBankRepository bloodBankRepository;
    private final StaffRepository staffRepository;
    private final WorkTimeRepository workTimeRepository;
    private final AddressRepository addressRepository;

    public BloodBankService(BloodBankRepository bloodBankRepository, StaffRepository staffRepository,
                            WorkTimeRepository workTimeRepository, AddressRepository addressRepository) {
        this.bloodBankRepository = bloodBankRepository;
        this.staffRepository = staffRepository;
        this.workTimeRepository = workTimeRepository;
        this.addressRepository = addressRepository;
    }

    public BloodBank update(UpdateBloodBankDTO bloodBank, String email) {
        Staff staff = staffRepository.getStaffByAccount_Email(email);
        BloodBank bloodBankToUpdate = bloodBankRepository.getBloodBankById(staff.getBloodBank().getId());
        Address address = new Address();
        address.setNumber(bloodBank.getNumber());
        address.setStreet(bloodBank.getStreet());
        address.setCountry(bloodBank.getCountry());
        address.setCity(bloodBank.getCity());
        Long addressId = bloodBankToUpdate.getAddress() == null? 0: bloodBankToUpdate.getAddress().getId();
        bloodBankToUpdate.setAddress(address);
        WorkTime workTime = new WorkTime();
        workTime.setStartDate(bloodBank.getStartDate());
        workTime.setEndTime(bloodBank.getEndTime());
        workTime.setEndDate(bloodBank.getEndDate());
        workTime.setStartTime(bloodBank.getStartTime());
        Long workTimeId = bloodBankToUpdate.getWorkTime() == null? 0 : bloodBankToUpdate.getWorkTime().getId();
        bloodBankToUpdate.setWorkTime(workTime);
        bloodBankToUpdate.setName(bloodBank.getName());
        bloodBankToUpdate.setDescription(bloodBank.getDescription());
        bloodBankToUpdate.setAverageRating(bloodBank.getAverageRating());
        BloodBank bb = bloodBankRepository.save(bloodBankToUpdate);
        if(addressId > 0)
        addressRepository.deleteById(addressId);
        if(workTimeId > 0)
            workTimeRepository.deleteById(workTimeId);
        return bb;
    }
}
