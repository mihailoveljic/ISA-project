package eu.dreamTeam.isabackend.model;

import eu.dreamTeam.isabackend.model.enums.Gender;
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
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String text;
    @Column
    private boolean acceptableAnswer;
    @Enumerated(EnumType.STRING)
    @Column(name = "for_gender")
    private Gender forGender;
}
