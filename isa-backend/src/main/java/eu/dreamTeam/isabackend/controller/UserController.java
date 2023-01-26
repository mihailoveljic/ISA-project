package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.handler.exceptions.*;
import eu.dreamTeam.isabackend.model.User;
import eu.dreamTeam.isabackend.service.AccountService;
import eu.dreamTeam.isabackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @PreAuthorize("hasAnyRole('user, 'staff', 'admin')")
    @GetMapping
    public ResponseEntity<User> getInfoByEmail(
            @RequestParam String email) {
        User user = userService.getByEmail(email);
        if (user == null)
            throw new StaffNotExistedException();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
