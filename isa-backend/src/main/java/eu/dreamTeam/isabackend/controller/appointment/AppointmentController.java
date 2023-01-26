package eu.dreamTeam.isabackend.controller.appointment;

import eu.dreamTeam.isabackend.dto.*;
import eu.dreamTeam.isabackend.model.enums.BloodType;
import eu.dreamTeam.isabackend.model.enums.EquipmentType;
import eu.dreamTeam.isabackend.service.BloodSampleService;
import eu.dreamTeam.isabackend.service.EquipmentService;
import eu.dreamTeam.isabackend.service.appointment.AppointmentService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    @Autowired
    public AppointmentService appointmentService;
    @Autowired
    public BloodSampleService bloodSampleService;
    @Autowired
    public EquipmentService equipmentService;
    @PostMapping
    public ResponseEntity<CreateAppointmentDTO> createAppointment(@RequestBody CreateAppointmentDTO createAppointmentDTO) throws InterruptedException {
        appointmentService.createAppointment(createAppointmentDTO);
        return new ResponseEntity<>(createAppointmentDTO, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('user', 'staff', 'admin')")
    @GetMapping(value = "/freeAppointments")
    public ResponseEntity<ScheduledAppointmentsDTOs> getAllFreeAppointments(){
        ScheduledAppointmentsDTOs scheduledAppointmentsDTOs = new ScheduledAppointmentsDTOs();
        scheduledAppointmentsDTOs.setScheduleAppointmentDTOS(appointmentService.getAllFreeAppointments());
        return new ResponseEntity<>(scheduledAppointmentsDTOs ,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('user', 'staff', 'admin')")
    @GetMapping(value = "/freeAppointmentsByBloodBankId/{bloodBankId}")
    public ResponseEntity<ScheduledAppointmentsDTOs> getAllFreeAppointmentsByBloodBankId(@PathVariable Long bloodBankId){
        ScheduledAppointmentsDTOs scheduledAppointmentsDTOs = new ScheduledAppointmentsDTOs();
        scheduledAppointmentsDTOs.setScheduleAppointmentDTOS(appointmentService.getAllFreeAppointmentsByBloodBankId(bloodBankId));
        return new ResponseEntity<>(scheduledAppointmentsDTOs ,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('user', 'staff', 'admin')")
    @GetMapping(value = "/getAllAppointmentsByUserEmail/{userEmail}")
    public ResponseEntity<ScheduledAppointmentsDTOs> getAllAppointmentsByUserEmail(@PathVariable String userEmail){
        ScheduledAppointmentsDTOs scheduledAppointmentsDTOs = new ScheduledAppointmentsDTOs();
        scheduledAppointmentsDTOs.setScheduleAppointmentDTOS(appointmentService.getAllAppointmentsByUserEmail(userEmail));
        return new ResponseEntity<>(scheduledAppointmentsDTOs ,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('user', 'staff', 'admin')")
    @GetMapping(value = "/checkForAppointmentInLast6Months/{userEmail}")
    public ResponseEntity checkForAppointmentInLast6Months(@PathVariable String userEmail){
        var response = appointmentService.checkForAppointmentInLast6Months(userEmail);
        return new ResponseEntity<>(response ,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('user', 'staff', 'admin')")
    @GetMapping(value = "/allAppointmentsBySelectedDateTime/{selectedDateTime}")
    public ResponseEntity<ScheduledAppointmentsDTOs> getAllAppointmentsBySelectedDateTime(@PathVariable String selectedDateTime){
        ScheduledAppointmentsDTOs scheduledAppointmentsDTOs = new ScheduledAppointmentsDTOs();
        scheduledAppointmentsDTOs.setScheduleAppointmentDTOS(appointmentService.getAllAppointmentsBySelectedDateTime(selectedDateTime));
        return new ResponseEntity<ScheduledAppointmentsDTOs>(scheduledAppointmentsDTOs ,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('user', 'staff', 'admin')")
    @PutMapping(value = "/scheduleAppointment")
    public ResponseEntity<ScheduleAppointmentDTO> scheduleAppointment(@RequestBody ScheduleAppointmentDTO scheduleAppointmentDTO) {
        scheduleAppointmentDTO = appointmentService.scheduleAppointment(scheduleAppointmentDTO);
        return new ResponseEntity<>(scheduleAppointmentDTO ,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('user', 'staff', 'admin')")
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
    @PreAuthorize("hasAnyRole('user', 'staff', 'admin')")
    @GetMapping(value = "/allAppointments")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments(){
        List<AppointmentDTO> AppointmentsDTOs = appointmentService.getAllAppointments();
        return new ResponseEntity<>(AppointmentsDTOs ,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('user', 'staff', 'admin')")
    @GetMapping(value = "/cancel")
    public ResponseEntity<StringDTO> cancel(@RequestParam Long id){
        StringDTO string = new StringDTO();
        appointmentService.cancel(id);
        return new ResponseEntity<>(string, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('user', 'staff', 'admin')")
    @GetMapping(value = "/finish")
    public ResponseEntity<StringDTO> finish(
            @RequestParam Long id,
            @RequestParam String text,
            @RequestParam String bloodType,
            @RequestParam double amount,
            @RequestParam String equipmentType1,
            @RequestParam double equipmentAmount1,
            @RequestParam String equipmentType2,
            @RequestParam double equipmentAmount2){
        StringDTO string = new StringDTO();
        if(bloodSampleService.getBloodSample(bloodType) < 1){
            string.setText("Blood type doesn't exist");
            return new ResponseEntity<>(string, HttpStatus.NOT_FOUND);
        }
        if(bloodSampleService.getBloodSampleAmount(bloodType)<amount){
            string.setText("Blood type doesn't exist in needed amount");
            return new ResponseEntity<>(string, HttpStatus.NOT_FOUND);
        }
        if(equipmentService.getEquipment(equipmentType1) < 1){
            string.setText("Equipment 1 doesn't exist");
            return new ResponseEntity<>(string, HttpStatus.NOT_FOUND);
        }
        if(equipmentService.getEquipmentAmount(equipmentType1)<equipmentAmount1){
            string.setText("Equipment 1 doesn't exist in needed amount");
            return new ResponseEntity<>(string, HttpStatus.NOT_FOUND);
        }
        if(equipmentService.getEquipment(equipmentType2) < 1){
            string.setText("Equipment 2 doesn't exist");
            return new ResponseEntity<>(string, HttpStatus.NOT_FOUND);
        }
        if(equipmentService.getEquipmentAmount(equipmentType2)<equipmentAmount2){
            string.setText("Equipment 2 doesn't exist in needed amount");
            return new ResponseEntity<>(string, HttpStatus.NOT_FOUND);
        }
        bloodSampleService.substractBloodSamples(BloodType.valueOf(bloodType), amount);
        equipmentService.substractEquipment(EquipmentType.valueOf(equipmentType1), equipmentAmount1);
        equipmentService.substractEquipment(EquipmentType.valueOf(equipmentType2), equipmentAmount2);
        appointmentService.finish(id, text);
        return new ResponseEntity<>(string, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('user', 'staff', 'admin')")
    @GetMapping(value = "/didnt-appear")
    public ResponseEntity<StringDTO> didntAppear(@RequestParam Long id, @RequestParam String email){
        StringDTO string = new StringDTO();
        appointmentService.didntAppear(id, email);
        return new ResponseEntity<>(string, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('user', 'staff', 'admin')")
    @GetMapping(value = "/user-appointments/{email}")
    public ResponseEntity<List<AppointmentDTO>> getUserAppointments(@PathVariable String email){
        List<AppointmentDTO> AppointmentsDTOs = appointmentService.getUserAppointments(email);
        return new ResponseEntity<>(AppointmentsDTOs ,HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('user', 'staff', 'admin')")
    @PostMapping("/upload")
    public ResponseEntity<AppointmentDTO> singleFileUpload(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {

            return null;
            //TODO error dodaj
        }
        try {
            File convFile = new File(file.getOriginalFilename());
            file.transferTo(convFile);
            AppointmentDTO dto = appointmentService.getTextFromAppointmentQR(convFile);
            if (dto == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(dto ,HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
