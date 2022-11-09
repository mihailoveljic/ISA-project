package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.dto.StaffDTO;
import eu.dreamTeam.isabackend.handler.exceptions.AccountNotExistedException;
import eu.dreamTeam.isabackend.handler.exceptions.StaffNotExistedException;
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
    public ResponseEntity<String> updateInfo(
            @RequestBody @Valid StaffDTO updateStaffDTO,
            @RequestParam String email) {
        if(!accountService.check(email))
            return new ResponseEntity<>("Account doesn't exist.", HttpStatus.NOT_FOUND);
        Staff staffUpdated = staffService.update(updateStaffDTO);
        if (staffUpdated == null)
            return new ResponseEntity<>("Failed to update entity.", HttpStatus.CONFLICT);
        return new ResponseEntity<>("Updated entity successfully.", HttpStatus.OK);
    }

    @PutMapping(value = "/password")
    public ResponseEntity<String> updatePassword(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String newPassword) {
        Account currentAccount = accountService.validate(email, password);
        if(currentAccount == null)
            return new ResponseEntity<>("Account doesn't exist.", HttpStatus.NOT_FOUND);
        if(newPassword.length() < 8)
            return new ResponseEntity<>("New password is too short.", HttpStatus.BAD_REQUEST);
        if (!newPassword.matches(numRegex))
            return new ResponseEntity<>("New password doesn't contain number.", HttpStatus.BAD_REQUEST);
        if(!newPassword.matches(bigAlphaRegex) || !newPassword.matches(smallAlphaRegex))
            return new ResponseEntity<>("New password doesn't contain letter.", HttpStatus.BAD_REQUEST);
        Account account = accountService.updatePassword(currentAccount, newPassword);
        if (account == null)
            return new ResponseEntity<>("Current password is incorrect.", HttpStatus.CONFLICT);
        return new ResponseEntity<>("Updated account successfully.", HttpStatus.OK);
    }
}
