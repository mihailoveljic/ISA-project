package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.dto.StaffDTO;
import eu.dreamTeam.isabackend.dto.StaffWithCenterDTO;
import eu.dreamTeam.isabackend.dto.UpdatePasswordDTO;
import eu.dreamTeam.isabackend.handler.exceptions.*;
import eu.dreamTeam.isabackend.model.Account;
import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.model.User;
import eu.dreamTeam.isabackend.service.AccountService;
import eu.dreamTeam.isabackend.service.StaffService;
import eu.dreamTeam.isabackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<User> getInfoByEmail(
            @RequestParam String email) {
        if(!accountService.check(email))
            throw new AccountNotExistedException();
        User user = userService.getByEmail(email);
        if (user == null)
            throw new StaffNotExistedException();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
