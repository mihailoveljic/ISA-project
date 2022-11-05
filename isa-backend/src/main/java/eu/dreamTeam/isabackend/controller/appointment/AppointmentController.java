package eu.dreamTeam.isabackend.controller.appointment;

import eu.dreamTeam.isabackend.dto.CreateAppointmentDTO;
import eu.dreamTeam.isabackend.dto.UpdateBloodBankDTO;
import eu.dreamTeam.isabackend.model.BloodBank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    @PostMapping
    public ResponseEntity<CreateAppointmentDTO> createAppointment(@RequestBody CreateAppointmentDTO createAppointmentDTO) {
        return new ResponseEntity<>(createAppointmentDTO, HttpStatus.OK);
    }
}
