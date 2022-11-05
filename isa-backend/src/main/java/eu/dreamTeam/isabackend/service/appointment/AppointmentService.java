package eu.dreamTeam.isabackend.service.appointment;

import eu.dreamTeam.isabackend.dto.CreateAppointmentDTO;
import eu.dreamTeam.isabackend.model.Appointment;
import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.model.enums.AppointmentStatus;
import eu.dreamTeam.isabackend.repository.AppointmentRepository;
import eu.dreamTeam.isabackend.repository.BloodBankRepository;
import eu.dreamTeam.isabackend.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashSet;
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
        Appointment appointment = CreateAppointmentDTOToEntity(createAppointmentDTO);
        appointmentRepository.save(appointment);
        return  createAppointmentDTO;
}

    private Appointment CreateAppointmentDTOToEntity(CreateAppointmentDTO createAppointmentDTO) {
        LocalDateTime localDateTime = TransformStringToLocalDateTime(createAppointmentDTO.getDate());//not implemented
        Set<Staff> staffs = new HashSet<>();
        for(Long id : createAppointmentDTO.getStaff()){
            staffs.add(staffRepository.findById(id).stream().findFirst().orElse(null));
        }
        return  Appointment.builder()
                .date(localDateTime)
                .duration(createAppointmentDTO.getDuration())
                .status(AppointmentStatus.SCHEDULED)
                .description(createAppointmentDTO.getDescription())
                .price(createAppointmentDTO.getPrice())
                .bloodBankForAppointment(bloodBankRepository.findById(createAppointmentDTO.getBloodBankForAppointment()).stream().findFirst().orElse(null))
                .staff(staffs)
                .build();
    }

    private LocalDateTime TransformStringToLocalDateTime(String date) {
        String[] parts = date.split("-");
        LocalDateTime localDateTime = LocalDateTime.of(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]) -1, Integer.valueOf(parts[2]), Integer.valueOf(parts[3]), 0, 0);
        return localDateTime;
    }
}
