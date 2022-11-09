package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.dto.BloodBankDTO;
import eu.dreamTeam.isabackend.model.BloodBank;
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

    public BloodBank update(BloodBankDTO bloodBank) {
        BloodBank bloodBankToUpdate = bloodBankRepository.getBloodBankById(bloodBank.getId());
        bloodBankToUpdate.getAddress().setNumber(bloodBank.getNumber());
        bloodBankToUpdate.getAddress().setStreet(bloodBank.getStreet());
        bloodBankToUpdate.getAddress().setCountry(bloodBank.getCountry());
        bloodBankToUpdate.getAddress().setCity(bloodBank.getCity());
        bloodBankToUpdate.getWorkTime().setEndTime(bloodBank.getEndTime());
        bloodBankToUpdate.getWorkTime().setStartTime(bloodBank.getStartTime());
        bloodBankToUpdate.setName(bloodBank.getName());
        bloodBankToUpdate.setDescription(bloodBank.getDescription());
        bloodBankToUpdate.setAverageRating(bloodBank.getAverageRating());
        return bloodBankRepository.save(bloodBankToUpdate);
    }

    public BloodBank getByStaffEmail(String email) {
        return bloodBankRepository.getBloodBankByStaffEmail(email);
    }
}
