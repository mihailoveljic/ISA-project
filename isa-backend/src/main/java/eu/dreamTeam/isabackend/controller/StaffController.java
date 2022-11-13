package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.dto.StaffDTO;
import eu.dreamTeam.isabackend.dto.StaffWithCenterDTO;
import eu.dreamTeam.isabackend.dto.UpdatePasswordDTO;
import eu.dreamTeam.isabackend.handler.exceptions.*;
import eu.dreamTeam.isabackend.model.Account;
import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.service.AccountService;
import eu.dreamTeam.isabackend.service.StaffService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;
    private final AccountService accountService;
    private final ModelMapper modelMapper;
    private final String numRegex   = ".*[0-9].*";
    private final String bigAlphaRegex = ".*[A-Z].*";
    private final String smallAlphaRegex = ".*[A-Z].*";

    public StaffController(StaffService staffService, AccountService accountService, ModelMapper modelMapper) {
        this.staffService = staffService;
        this.accountService = accountService;
        this.modelMapper = modelMapper;
        TypeMap<Staff, StaffDTO> propertyMapper = modelMapper.createTypeMap(Staff.class, StaffDTO.class);
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getCity(), StaffDTO::setCity));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getStreet(), StaffDTO::setStreet));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getCountry(), StaffDTO::setCountry));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAddress().getNumber(), StaffDTO::setNumber));
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getAccount().getEmail(), StaffDTO::setEmail));
    }

    @GetMapping
    public ResponseEntity<StaffDTO> getInfoByEmail(
            @RequestParam String email) {
        if(!accountService.check(email))
            throw new AccountNotExistedException();
        Staff staff = staffService.getByEmail(email);
        StaffDTO staffDTO = modelMapper.map(staff, StaffDTO.class);
        if (staff == null)
            throw new StaffNotExistedException();
        return new ResponseEntity<>(staffDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StaffDTO> updateInfo(
            @RequestBody @Valid StaffDTO updateStaffDTO) {
        Staff staffUpdated = staffService.update(updateStaffDTO);
        if (staffUpdated == null)
            throw new FailedUpdateException();
        return new ResponseEntity<>(updateStaffDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StaffWithCenterDTO> create(
            @RequestBody @Valid StaffWithCenterDTO createStaffDTO) {
        if (accountService.check(createStaffDTO.getEmail()))
            throw new NotUniqueEmailException();
        if(staffService.checkStaffByJmbg(createStaffDTO.getJmbg()))
            throw new NotUniqueJmbgException();
        Staff staff = staffService.create(createStaffDTO);
        if (staff == null)
            throw new FailedUpdateException(); //TODO
        return new ResponseEntity<>(createStaffDTO, HttpStatus.OK);
    }

    @PutMapping("/password")
    public ResponseEntity<UpdatePasswordDTO> updatePassword(
            @RequestBody @Valid UpdatePasswordDTO updatePasswordDTO) {
        Account currentAccount = accountService.validate(updatePasswordDTO.getEmail(), updatePasswordDTO.getPassword());
        if(currentAccount == null)
            throw new InvalidPasswordException();
        if(updatePasswordDTO.getNewPassword().length() < 8)
            throw new TooShortPasswordException();
        if (!updatePasswordDTO.getNewPassword().matches(numRegex))
            throw new PasswordDoesntContainNumberException();
        if(!updatePasswordDTO.getNewPassword().matches(bigAlphaRegex) || !updatePasswordDTO.getNewPassword().matches(smallAlphaRegex))
            throw new PasswordDoesntContainLetterException();
        Account account = accountService.updatePassword(currentAccount, updatePasswordDTO.getNewPassword());
        if (account == null)
            throw new FailedUpdateException();
        return new ResponseEntity<>(updatePasswordDTO, HttpStatus.OK);
    }
}
