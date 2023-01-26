package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.dto.StaffDTO;
import eu.dreamTeam.isabackend.dto.StaffMainInfoDTO;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;
    private final AccountService accountService;
    private final ModelMapper modelMapper;
    private final String numRegex   = ".*[0-9].*";
    private final String bigAlphaRegex = ".*[A-Z].*";
    private final String smallAlphaRegex = ".*[a-z].*";

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
    @PreAuthorize("hasAnyRole('staff', 'admin')")
    @GetMapping
    public ResponseEntity<StaffDTO> getInfoByEmail(
            @RequestParam String email) {
        Staff staff = staffService.getByEmail(email);
        StaffDTO staffDTO = modelMapper.map(staff, StaffDTO.class);
        if (staff == null)
            throw new StaffNotExistedException();
        return new ResponseEntity<>(staffDTO, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('staff', 'admin')")
    @GetMapping("/colleagues")
    public ResponseEntity<List<StaffMainInfoDTO>> getStaffForCenter(
            @RequestParam String email) {
        Staff staff = staffService.getByEmail(email);
        if (staff == null)
            throw new StaffNotExistedException();
        List<Staff> staffList = staffService.getStaffFromSameCenter(staff.getId(),staff.getBloodBank().getId());
        List<StaffMainInfoDTO> staffMainInfoDTOS = new ArrayList<>();
        for(Staff s: staffList){
            StaffMainInfoDTO staffMainInfoDTO = modelMapper.map(s, StaffMainInfoDTO.class);
            staffMainInfoDTOS.add(staffMainInfoDTO);
        }
        return new ResponseEntity<>(staffMainInfoDTOS, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('staff', 'admin')")
    @PutMapping
    public ResponseEntity<StaffDTO> updateInfo(
            @RequestBody @Valid StaffDTO updateStaffDTO) {
        Staff staffUpdated = staffService.update(updateStaffDTO);
        if (staffUpdated == null)
            throw new FailedUpdateException();
        return new ResponseEntity<>(updateStaffDTO, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('staff', 'admin')")
    @PostMapping
    public ResponseEntity<StaffWithCenterDTO> create(
            @RequestBody @Valid StaffWithCenterDTO createStaffDTO) {
        if (accountService.check(createStaffDTO.getEmail()))
            throw new NotUniqueEmailException();
        if(staffService.checkStaffByJmbg(createStaffDTO.getJmbg()))
            throw new NotUniqueJmbgException();
        Staff staff = staffService.create(createStaffDTO);
        if (staff == null)
            throw new FailedUpdateException();
        return new ResponseEntity<>(createStaffDTO, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('staff', 'admin')")
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
        if(!(updatePasswordDTO.getNewPassword().matches(bigAlphaRegex) || updatePasswordDTO.getNewPassword().matches(smallAlphaRegex)))
            throw new PasswordDoesntContainLetterException();
        Account account = accountService.updatePassword(currentAccount, updatePasswordDTO.getNewPassword());
        if (account == null)
            throw new FailedUpdateException();
        return new ResponseEntity<>(updatePasswordDTO, HttpStatus.OK);
    }
}
