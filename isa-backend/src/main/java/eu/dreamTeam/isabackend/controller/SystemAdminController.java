package eu.dreamTeam.isabackend.controller;

import eu.dreamTeam.isabackend.dto.AdminDTO;
import eu.dreamTeam.isabackend.dto.StaffWithCenterDTO;
import eu.dreamTeam.isabackend.handler.exceptions.FailedUpdateException;
import eu.dreamTeam.isabackend.handler.exceptions.NotUniqueEmailException;
import eu.dreamTeam.isabackend.handler.exceptions.NotUniqueJmbgException;
import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.model.SystemAdmin;
import eu.dreamTeam.isabackend.service.AccountService;
import eu.dreamTeam.isabackend.service.SystemAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class SystemAdminController {

    private final SystemAdminService adminService;

    private final AccountService accountService;

    public SystemAdminController(SystemAdminService adminService, AccountService accountService) {
        this.adminService = adminService;
        this.accountService = accountService;
    }

    @PreAuthorize("hasAnyRole('staff', 'admin')")
    @PostMapping
    public ResponseEntity<AdminDTO> create(
            @RequestBody @Valid AdminDTO adminDTO) {
        if (accountService.check(adminDTO.getEmail()))
            throw new NotUniqueEmailException();
        if(adminService.checkAdminByJmbg(adminDTO.getJmbg()))
            throw new NotUniqueJmbgException();
        SystemAdmin admin = adminService.create(adminDTO);
        if (admin == null)
            throw new FailedUpdateException();
        return new ResponseEntity<>(adminDTO, HttpStatus.OK);
    }
}
