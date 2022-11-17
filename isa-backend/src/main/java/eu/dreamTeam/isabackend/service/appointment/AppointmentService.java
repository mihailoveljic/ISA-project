package eu.dreamTeam.isabackend.service.appointment;

import eu.dreamTeam.isabackend.dto.CreateAppointmentDTO;
import eu.dreamTeam.isabackend.dto.ScheduleAppointmentDTO;
import eu.dreamTeam.isabackend.handler.exceptions.InvalidCreateAppointmentDTOException;
import eu.dreamTeam.isabackend.model.Appointment;
import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.model.enums.AppointmentStatus;
import eu.dreamTeam.isabackend.repository.AppointmentRepository;
import eu.dreamTeam.isabackend.repository.BloodBankRepository;
import eu.dreamTeam.isabackend.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private BloodBankRepository bloodBankRepository;
    @Autowired
    private StaffRepository staffRepository;

    public CreateAppointmentDTO createAppointment(CreateAppointmentDTO createAppointmentDTO) {
        try {
            Appointment appointment = CreateAppointmentDTOToEntity(createAppointmentDTO);
            appointmentRepository.save(appointment);
            return  createAppointmentDTO;
        }catch (Exception ex){
            throw new InvalidCreateAppointmentDTOException();
        }
}

    private Appointment CreateAppointmentDTOToEntity(CreateAppointmentDTO createAppointmentDTO) {
        LocalDateTime localDateTime = TransformStringToLocalDateTime(createAppointmentDTO.getDate());
        Set<Staff> staffs = new HashSet<>();
        for(Long id : createAppointmentDTO.getStaff()){
            staffs.add(staffRepository.findById(id).stream().findFirst().orElse(null));
        }
        if(createAppointmentDTO.getPrice() < 0) throw new InvalidCreateAppointmentDTOException();
        return  Appointment.builder()
                .date(localDateTime)
                .duration(createAppointmentDTO.getDuration())
                .status(AppointmentStatus.FREE)
                .description(createAppointmentDTO.getDescription())
                .price(createAppointmentDTO.getPrice())
                .bloodBankForAppointment(bloodBankRepository.findById(createAppointmentDTO.getBloodBankForAppointment()).stream().findFirst().orElse(null))
                .staff(staffs)
                .build();
    }

    private LocalDateTime TransformStringToLocalDateTime(String date) {
        String[] parts = date.split("-");
        LocalDateTime localDateTime = LocalDateTime.of(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]), Integer.valueOf(parts[2]), Integer.valueOf(parts[3]), Integer.valueOf(parts[4]), 0);
        LocalDateTime currentDateTime = LocalDateTime.now();
        if(currentDateTime.isAfter(localDateTime)) throw new InvalidCreateAppointmentDTOException();
        return localDateTime;
    }

    public List<ScheduleAppointmentDTO> getAllFreeAppointments() {
        try {
             var freeAppointments = appointmentRepository.findAllFreeAppointments();
             List<ScheduleAppointmentDTO> scheduledAppointmentsDTOs = new ArrayList<ScheduleAppointmentDTO>();
             for(Appointment a : freeAppointments) {
                 scheduledAppointmentsDTOs.add(FromAppointmentToScheduleAppointmentDto(a));
             }
             return scheduledAppointmentsDTOs;
        } catch (Exception e) {
            return null;
        }
    }

    private ScheduleAppointmentDTO FromAppointmentToScheduleAppointmentDto(Appointment freeAppointment) {
        return ScheduleAppointmentDTO.builder()
                .id(freeAppointment.getId())
                .date(freeAppointment.getDate().toString())
                .duration(freeAppointment.getDuration())
                .description(freeAppointment.getDescription())
                .price(freeAppointment.getPrice())
                .bloodBankForAppointment(null)
                .staff(null)
                .build();
    }

    public ScheduleAppointmentDTO scheduleAppointment(ScheduleAppointmentDTO scheduleAppointmentDTO) {
        Appointment appointment = appointmentRepository.findById(scheduleAppointmentDTO.getId()).stream().findFirst().orElse(null);
        appointment.setStatus(AppointmentStatus.SCHEDULED);
        appointmentRepository.save(appointment);
        return scheduleAppointmentDTO;
    }
}
