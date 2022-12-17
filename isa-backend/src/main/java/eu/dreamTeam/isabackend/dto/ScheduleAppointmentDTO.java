package eu.dreamTeam.isabackend.dto;

import eu.dreamTeam.isabackend.model.enums.AppointmentStatus;
import lombok.*;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleAppointmentDTO {
    private Long id;
    private String date;
    private int duration;
    private String description;
    private double price;
    private Long bloodBankForAppointment;
    private Set<Long> staff;
    private String userEmail;
    private AppointmentStatus appointmentStatus;
}
