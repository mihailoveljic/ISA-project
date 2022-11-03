package eu.dreamTeam.isabackend.model;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class SystemAdmin extends Person{
    @OneToMany(mappedBy = "systemAdmin")
    private Set<BloodBank> bloodBanks;
}
