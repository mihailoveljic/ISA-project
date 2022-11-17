package eu.dreamTeam.isabackend.dto;

import eu.dreamTeam.isabackend.model.enums.AppointmentStatus;
import lombok.*;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAppointmentDTO {
    private String date;
    private int duration;
    private AppointmentStatus status;
    private String description;
    private double price;
    private Long bloodBankForAppointment;
    private Set<Long> staff;
}
