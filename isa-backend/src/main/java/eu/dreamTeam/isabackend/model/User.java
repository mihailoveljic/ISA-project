package eu.dreamTeam.isabackend.model;

import eu.dreamTeam.isabackend.model.enums.UserType;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User extends Person{
    @Column
    private int collectedPoints;
    @Column
    private UserType userType;
    @ManyToMany
    @JoinTable(
            name = "user_questionnaire",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "questionnaire_id")
    )
    private Set<Questionnaire> questionnaires;
}
