package eu.dreamTeam.isabackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private double averageRating;
    @Column
    private boolean isDeleted;
    @OneToMany(mappedBy = "bloodBank")
    private Set<BloodSample> bloodSamples;
    @OneToMany(mappedBy = "bloodBank")
    private Set<Equipment> equipment;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "worktime_id")
    private WorkTime workTime;
    @OneToMany(mappedBy = "bloodBankSupplier")
    private Set<Delivery> suppliedDeliveries;
    @OneToMany(mappedBy = "bloodBankReceiver")
    private Set<Delivery> receivedDeliveries;
    @OneToMany(mappedBy = "bloodBankForAppointment")
    private Set<Appointment> appointments;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private SystemAdmin systemAdmin;
    @OneToMany(mappedBy = "bloodBank")
    private Set<Staff> staff;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

}
