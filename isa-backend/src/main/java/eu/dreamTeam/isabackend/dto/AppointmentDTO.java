package eu.dreamTeam.isabackend.dto;

import eu.dreamTeam.isabackend.model.enums.AppointmentStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AppointmentDTO {
    @NotNull
    private Long id;
    private String date;
    private int duration;
    private String description;
    private String user;
    private AppointmentStatus appointmentStatus;
}
