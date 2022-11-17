package eu.dreamTeam.isabackend.model;

import eu.dreamTeam.isabackend.model.enums.BloodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodSample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private BloodType bloodType;
    @Column
    private double amount;
    @ManyToOne
    @JoinColumn(name = "blood_bank_id")
    private BloodBank bloodBank;
    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

}
