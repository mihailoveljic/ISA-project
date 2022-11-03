package eu.dreamTeam.isabackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import eu.dreamTeam.isabackend.model.enums.AppointmentStatus;
import eu.dreamTeam.isabackend.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String phoneNumber;
    @Column(unique = true, length = 13)
    private String jmbg;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private String profession;
    @Column
    private String professionInfo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account-id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
