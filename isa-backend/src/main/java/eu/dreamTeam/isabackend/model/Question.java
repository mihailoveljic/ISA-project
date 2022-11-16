package eu.dreamTeam.isabackend.model;

import eu.dreamTeam.isabackend.model.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
