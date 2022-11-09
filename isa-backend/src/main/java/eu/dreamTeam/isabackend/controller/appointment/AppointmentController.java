package eu.dreamTeam.isabackend.controller.appointment;

import eu.dreamTeam.isabackend.dto.CreateAppointmentDTO;
import eu.dreamTeam.isabackend.dto.ScheduleAppointmentDTO;
import eu.dreamTeam.isabackend.dto.ScheduledAppointmentsDTOs;
import eu.dreamTeam.isabackend.service.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<ScheduledAppointmentsDTOs>(scheduledAppointmentsDTOs ,HttpStatus.OK);
    }
    @PutMapping(value = "/scheduleAppointment")
    public ResponseEntity<ScheduleAppointmentDTO> scheduleAppointment(@RequestBody ScheduleAppointmentDTO scheduleAppointmentDTO) {
        scheduleAppointmentDTO = appointmentService.scheduleAppointment(scheduleAppointmentDTO);
        return new ResponseEntity<ScheduleAppointmentDTO>(scheduleAppointmentDTO ,HttpStatus.OK);
    }
}
