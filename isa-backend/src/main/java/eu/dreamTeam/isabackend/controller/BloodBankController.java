package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.dto.UpdateBloodBankDTO;
import eu.dreamTeam.isabackend.model.BloodBank;
import eu.dreamTeam.isabackend.service.AccountService;
import eu.dreamTeam.isabackend.service.BloodBankService;
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

    @PutMapping
    public ResponseEntity<String> updateInfo(
            @RequestBody @Valid UpdateBloodBankDTO updateBloodBankDTO,
            @RequestParam String email,
            @RequestParam String password) {
        if(!accountService.validate(email, password))
            return new ResponseEntity<>("Account not existed.", HttpStatus.NOT_FOUND);
        if(updateBloodBankDTO.getEndDate().isBefore(updateBloodBankDTO.getStartDate()))
            return new ResponseEntity<>("End date is before start date.", HttpStatus.BAD_REQUEST);
        if(updateBloodBankDTO.getEndTime().isBefore(updateBloodBankDTO.getStartTime()))
            return new ResponseEntity<>("End time is before start time.", HttpStatus.BAD_REQUEST);
        BloodBank bloodBank = bloodBankService.update(updateBloodBankDTO, email);
        if (bloodBank == null)
            return new ResponseEntity<>("Failed to update entity.", HttpStatus.CONFLICT);
        return new ResponseEntity<>("Updated entity successfully.", HttpStatus.OK);
    }
}
