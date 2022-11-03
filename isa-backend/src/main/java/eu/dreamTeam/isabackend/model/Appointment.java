package eu.dreamTeam.isabackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import eu.dreamTeam.isabackend.model.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy. HH:mm")
    @Column
    private LocalDateTime date;
    @Column
    private int duration;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    @Column
    private String description;
    @Column
    private double price;
    @ManyToOne
    @JoinColumn(name = "blood_bank_id")
    private BloodBank bloodBankForAppointment;
}
