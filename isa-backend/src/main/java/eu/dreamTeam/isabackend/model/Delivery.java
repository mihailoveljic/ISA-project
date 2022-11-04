package eu.dreamTeam.isabackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import eu.dreamTeam.isabackend.model.enums.DayName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy. HH:mm")
    @Column
    private LocalDateTime postingDate;
    @ManyToOne
    @JoinColumn(name = "blood_bank_supplier_id")
    private BloodBank bloodBankSupplier;
    @ManyToOne
    @JoinColumn(name = "blood_bank_receiver_id")
    private BloodBank bloodBankReceiver;
    @OneToMany(mappedBy = "delivery")
    private Set<BloodSample> bloodSamples;
}
