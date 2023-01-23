package eu.dreamTeam.isabackend.service;

import eu.dreamTeam.isabackend.dto.BloodBankDTO;
import eu.dreamTeam.isabackend.model.*;
import eu.dreamTeam.isabackend.repository.AddressRepository;
import eu.dreamTeam.isabackend.repository.BloodBankRepository;
import eu.dreamTeam.isabackend.repository.StaffRepository;
import eu.dreamTeam.isabackend.repository.WorkTimeRepository;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Cacheable("bloodBank")
    public BloodBank getByStaffEmail(String email) {
        Staff staff = staffRepository.getStaffByAccount_Email(email);
        return bloodBankRepository.getBloodBankById(staff.getBloodBank().getId());
    }
    public BloodBank create(BloodBankDTO bloodBank) {
        BloodBank newBloodBank = new BloodBank();
        Address address = new Address();
        address.setStreet(bloodBank.getStreet());
        address.setNumber(bloodBank.getNumber());
        address.setCountry(bloodBank.getCountry());
        address.setCity(bloodBank.getCity());
        address = addressRepository.save(address);
        newBloodBank.setAddress(address);
        newBloodBank.setName(bloodBank.getName());
        newBloodBank.setDescription(bloodBank.getDescription());
        newBloodBank.setAverageRating(5);
        WorkTime workTime = new WorkTime();
        workTime.setStartTime(bloodBank.getStartTime());
        workTime.setEndTime(bloodBank.getEndTime());
        workTime = workTimeRepository.save(workTime);
        newBloodBank.setWorkTime(workTime);

        return bloodBankRepository.save(newBloodBank);
    }

    @RateLimiter(name = "standard", fallbackMethod = "standardFallback")
    public List<BloodBank> getAllBloodBanks(){
        return bloodBankRepository.findAll();
    }
    public List<BloodBank> standardFallback(RequestNotPermitted rnp) {
        throw rnp;
    }

    public List<BloodBank> getAll() {
        return bloodBankRepository.findAll();
    }
    public Page<BloodBank> getAllWithPageable(int page, int size, String order, String field) {
        Pageable pageable = getPageable(page,size,order, field);
        return bloodBankRepository.findAll(pageable);
    }
    private Pageable getPageable(int page, int size, String order, String field) {
        Pageable pageable = null;
        if (order.equals("none"))
            pageable = PageRequest.of(page, size, Sort.unsorted());
        else if (order.equals("asc"))
            pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(field)));
        if (order.equals("desc"))
            pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc(field)));
        return pageable;
    }
}
