package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.dto.UpdateStaffDTO;
import eu.dreamTeam.isabackend.model.Account;
import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.service.AccountService;
import eu.dreamTeam.isabackend.service.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;
    private final AccountService accountService;
    private final String numRegex   = ".*[0-9].*";
    private final String bigAlphaRegex = ".*[A-Z].*";
    private final String smallAlphaRegex = ".*[A-Z].*";

    public StaffController(StaffService staffService, AccountService accountService) {
        this.staffService = staffService;
        this.accountService = accountService;
    }

    @PutMapping
    public ResponseEntity<String> updateInfo(
            @RequestBody @Valid UpdateStaffDTO updateStaffDTO,
            @RequestParam String email,
            @RequestParam String password) {
        if(!accountService.validate(email, password))
            return new ResponseEntity<>("Account not existed.", HttpStatus.NOT_FOUND);
        Staff staffUpdated = staffService.update(updateStaffDTO, email);
        if (staffUpdated == null)
            return new ResponseEntity<>("Failed to update entity.", HttpStatus.CONFLICT);
        return new ResponseEntity<>("Updated entity successfully.", HttpStatus.OK);
    }

    @PutMapping(value = "/password")
    public ResponseEntity<String> updatePassword(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required = true) String newPassword) {
        if(!accountService.validate(email, password))
            return new ResponseEntity<>("Account not existed.", HttpStatus.NOT_FOUND);
        if(newPassword.length() < 8)
            return new ResponseEntity<>("Password too short.", HttpStatus.BAD_REQUEST);
        if (!newPassword.matches(numRegex))
            return new ResponseEntity<>("Password doesn't contain number.", HttpStatus.BAD_REQUEST);
        if(!newPassword.matches(bigAlphaRegex) || !newPassword.matches(smallAlphaRegex))
            return new ResponseEntity<>("Password doesn't contain letter.", HttpStatus.BAD_REQUEST);
        Account account = accountService.updatePassword(email, newPassword);
        if (account == null)
            return new ResponseEntity<>("Failed to update account.", HttpStatus.CONFLICT);
        return new ResponseEntity<>("Updated account successfully.", HttpStatus.OK);
    }
}
