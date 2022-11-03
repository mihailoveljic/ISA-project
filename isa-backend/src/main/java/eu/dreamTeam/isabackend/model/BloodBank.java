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
    @OneToMany(mappedBy = "bloodBank", cascade = CascadeType.ALL)
    private Set<BloodSample> bloodSamples;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "blood_bank_worktime",
            joinColumns = @JoinColumn(name = "blood_bank_id"),
            inverseJoinColumns = @JoinColumn(name = "worktime_id")
    )
    private Set<WorkTime> workTimes;
    @OneToMany(mappedBy = "bloodBankSupplier", cascade = CascadeType.ALL)
    private Set<Delivery> suppliedDeliveries;
    @OneToMany(mappedBy = "bloodBankReceiver", cascade = CascadeType.ALL)
    private Set<Delivery> receivedDeliveries;
    @OneToMany(mappedBy = "bloodBankForAppointment", cascade = CascadeType.ALL)
    private Set<Appointment> appointments;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private SystemAdmin systemAdmin;
    @OneToMany(mappedBy = "bloodBank")
    private Set<Staff> staff;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address-id")
    private Address address;
}
