package eu.dreamTeam.isabackend.controller.appointment;

import eu.dreamTeam.isabackend.dto.CreateAppointmentDTO;
import eu.dreamTeam.isabackend.dto.ScheduleAppointmentDTO;
import eu.dreamTeam.isabackend.dto.ScheduledAppointmentsDTOs;
import eu.dreamTeam.isabackend.service.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    @Autowired
    public AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<CreateAppointmentDTO> createAppointment(@RequestBody CreateAppointmentDTO createAppointmentDTO) {
        appointmentService.createAppointment(createAppointmentDTO);
        return new ResponseEntity<>(createAppointmentDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/freeAppointments")
    public ResponseEntity<ScheduledAppointmentsDTOs> getAllFreeAppointments(){
        ScheduledAppointmentsDTOs scheduledAppointmentsDTOs = new ScheduledAppointmentsDTOs();
        scheduledAppointmentsDTOs.setScheduleAppointmentDTOS(appointmentService.getAllFreeAppointments());
        return new ResponseEntity<>(scheduledAppointmentsDTOs ,HttpStatus.OK);
    }

    @GetMapping(value = "/freeAppointmentsByBloodBankId/{bloodBankId}")
    public ResponseEntity<ScheduledAppointmentsDTOs> getAllFreeAppointmentsByBloodBankId(@PathVariable Long bloodBankId){
        ScheduledAppointmentsDTOs scheduledAppointmentsDTOs = new ScheduledAppointmentsDTOs();
        scheduledAppointmentsDTOs.setScheduleAppointmentDTOS(appointmentService.getAllFreeAppointmentsByBloodBankId(bloodBankId));
        return new ResponseEntity<>(scheduledAppointmentsDTOs ,HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/getAllAppointmentsByUserEmail/{userEmail}")
    public ResponseEntity<ScheduledAppointmentsDTOs> getAllAppointmentsByUserEmail(@PathVariable String userEmail){
        ScheduledAppointmentsDTOs scheduledAppointmentsDTOs = new ScheduledAppointmentsDTOs();
        scheduledAppointmentsDTOs.setScheduleAppointmentDTOS(appointmentService.getAllAppointmentsByUserEmail(userEmail));
        return new ResponseEntity<>(scheduledAppointmentsDTOs ,HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/checkForAppointmentInLast6Months/{userEmail}")
    public ResponseEntity checkForAppointmentInLast6Months(@PathVariable String userEmail){
        var response = appointmentService.checkForAppointmentInLast6Months(userEmail);
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }

    @GetMapping(value = "/allAppointmentsBySelectedDateTime/{selectedDateTime}")
    public ResponseEntity<ScheduledAppointmentsDTOs> getAllAppointmentsBySelectedDateTime(@PathVariable String selectedDateTime){
        ScheduledAppointmentsDTOs scheduledAppointmentsDTOs = new ScheduledAppointmentsDTOs();
        scheduledAppointmentsDTOs.setScheduleAppointmentDTOS(appointmentService.getAllAppointmentsBySelectedDateTime(selectedDateTime));
        return new ResponseEntity<ScheduledAppointmentsDTOs>(scheduledAppointmentsDTOs ,HttpStatus.OK);
    }
    @PutMapping(value = "/scheduleAppointment")
    public ResponseEntity<ScheduleAppointmentDTO> scheduleAppointment(@RequestBody ScheduleAppointmentDTO scheduleAppointmentDTO) {
        scheduleAppointmentDTO = appointmentService.scheduleAppointment(scheduleAppointmentDTO);
        return new ResponseEntity<>(scheduleAppointmentDTO ,HttpStatus.OK);
    }

    @PutMapping(value = "/unscheduleAppointment")
    public ResponseEntity<ScheduleAppointmentDTO> unscheduleAppointment(@RequestBody ScheduleAppointmentDTO scheduleAppointmentDTO) {
        var now = LocalDateTime.now();
        now = now.plusDays(1);
        try {
            var appointmentDate = LocalDateTime.parse(scheduleAppointmentDTO.getDate());
            if(appointmentDate.isAfter(now)){
                scheduleAppointmentDTO = appointmentService.unscheduleAppointment(scheduleAppointmentDTO);
                return new ResponseEntity<>(scheduleAppointmentDTO ,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
