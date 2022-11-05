package eu.dreamTeam.isabackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import eu.dreamTeam.isabackend.model.BloodBank;
import eu.dreamTeam.isabackend.model.Staff;
import eu.dreamTeam.isabackend.model.enums.AppointmentStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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
