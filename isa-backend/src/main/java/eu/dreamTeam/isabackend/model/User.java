package eu.dreamTeam.isabackend.model;

import eu.dreamTeam.isabackend.model.enums.UserType;
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
@Table(name = "users")
public class User extends Person{
    @Column
    private int collectedPoints;
    @Column
    private UserType userType;
}
