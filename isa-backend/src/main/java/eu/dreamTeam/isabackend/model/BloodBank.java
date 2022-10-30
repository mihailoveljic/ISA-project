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
    private long id;
    @Column
    private String name;
    @Column
    private boolean isDeleted;
    @OneToMany(mappedBy = "bloodBank", cascade = CascadeType.ALL)
    private Set<ApiKey> apiKeys;
    @OneToMany(mappedBy = "bloodBank", cascade = CascadeType.ALL)
    private Set<BloodSample> bloodSamples;
}
