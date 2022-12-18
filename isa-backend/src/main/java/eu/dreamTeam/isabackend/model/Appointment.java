package eu.dreamTeam.isabackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import eu.dreamTeam.isabackend.model.enums.AppointmentStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @ManyToMany(mappedBy = "appointments")
    private Set<Staff> staff;
    @Column(name = "user_email")
    private String userEmail;


    @Override
    public String toString() {
        return  "Date: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")) +
                "\nDuration: " + duration + " min" +
                "\nDescription: " + description +
                "\nPrice: " + price + " din" +
                "\nCenter name: " + bloodBankForAppointment.getName() +
                "\nPatient Email: " + userEmail;
    }
}
