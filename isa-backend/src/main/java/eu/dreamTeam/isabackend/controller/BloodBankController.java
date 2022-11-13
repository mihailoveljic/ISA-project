package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.dto.BloodBankDTO;
import eu.dreamTeam.isabackend.dto.StaffDTO;
import eu.dreamTeam.isabackend.handler.exceptions.AccountNotExistedException;
import eu.dreamTeam.isabackend.handler.exceptions.BankNotExistedException;
import eu.dreamTeam.isabackend.handler.exceptions.FailedUpdateException;
import eu.dreamTeam.isabackend.handler.exceptions.WrongTimeRangeException;
import eu.dreamTeam.isabackend.model.BloodBank;
import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.service.AccountService;
import eu.dreamTeam.isabackend.service.BloodBankService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/blood-bank")
public class BloodBankController {

    @Autowired
    private BloodBankService bloodBankService;
    @Autowired
    private AccountService accountService;
    private final ModelMapper modelMapper;

    public BloodBankController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        TypeMap<BloodBank, BloodBankDTO> propertyMapper = modelMapper.createTypeMap(BloodBank.class, BloodBankDTO.class);
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getCity(), BloodBankDTO::setCity));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getStreet(), BloodBankDTO::setStreet));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getCountry(), BloodBankDTO::setCountry));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getNumber(), BloodBankDTO::setNumber));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getWorkTime().getStartTime(), BloodBankDTO::setStartTime));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getWorkTime().getEndTime(), BloodBankDTO::setEndTime));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getName(), BloodBankDTO::setName));

    }

    @GetMapping
    public ResponseEntity<BloodBankDTO> getInfoByStaffEmail(
            @RequestParam String email) {
        if(!accountService.check(email))
            throw new AccountNotExistedException();
        BloodBank bloodBank = bloodBankService.getByStaffEmail(email);
        if (bloodBank == null)
            throw new BankNotExistedException();
        BloodBankDTO bloodBankDTO = modelMapper.map(bloodBank, BloodBankDTO.class);
        return new ResponseEntity<>(bloodBankDTO, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<BloodBankDTO> updateInfo(
            @RequestBody @Valid BloodBankDTO updateBloodBankDTO) {
        if(updateBloodBankDTO.getEndTime().isBefore(updateBloodBankDTO.getStartTime()))
            throw new WrongTimeRangeException();
        BloodBank bloodBank = bloodBankService.update(updateBloodBankDTO);
        if (bloodBank == null)
            throw new FailedUpdateException();
        return new ResponseEntity<>(updateBloodBankDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<BloodBankDTO> createCenter(
            @RequestBody @Valid BloodBankDTO createBloodBankDTO) {
        if(createBloodBankDTO.getEndTime().isBefore(createBloodBankDTO.getStartTime()))
            throw new WrongTimeRangeException();
        BloodBank bloodBank = bloodBankService.create(createBloodBankDTO);
        if (bloodBank == null)
            throw new FailedUpdateException();
        return new ResponseEntity<>(createBloodBankDTO, HttpStatus.OK);
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<BloodBankDTO>> getAllBloodBanks(){
        List<BloodBank> banks = bloodBankService.getAllBloodBanks();
        List <BloodBankDTO>  bankDTOS = new ArrayList<BloodBankDTO>();
        for (BloodBank bank: banks){
            BloodBankDTO bankDTO = modelMapper.map(bank, BloodBankDTO.class);
            bankDTOS.add(bankDTO);
        }
        return new ResponseEntity<>(bankDTOS, HttpStatus.OK);
    }

}
