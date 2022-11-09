package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.dto.BloodBankDTO;
import eu.dreamTeam.isabackend.dto.StaffDTO;
import eu.dreamTeam.isabackend.handler.exceptions.AccountNotExistedException;
import eu.dreamTeam.isabackend.handler.exceptions.BankNotExistedException;
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
    public ResponseEntity<String> updateInfo(
            @RequestBody @Valid BloodBankDTO updateBloodBankDTO,
            @RequestParam String email) {
        if(!accountService.check(email))
            return new ResponseEntity<>("Account doesn't exist.", HttpStatus.NOT_FOUND);
        if(updateBloodBankDTO.getEndTime().isBefore(updateBloodBankDTO.getStartTime()))
            return new ResponseEntity<>("Entered end time is before start time.", HttpStatus.BAD_REQUEST);
        BloodBank bloodBank = bloodBankService.update(updateBloodBankDTO);
        if (bloodBank == null)
            return new ResponseEntity<>("Failed to update entity.", HttpStatus.CONFLICT);
        return new ResponseEntity<>("Updated entity successfully.", HttpStatus.OK);
    }
}
