package eu.dreamTeam.isabackend.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Set;

@Entity
public class Staff extends Person{
    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
    @ManyToOne
    @JoinColumn(name = "blood_bank_id")
    private BloodBank bloodBank;
}
