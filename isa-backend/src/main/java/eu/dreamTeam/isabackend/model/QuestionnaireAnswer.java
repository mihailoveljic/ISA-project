package eu.dreamTeam.isabackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "QuestionnaireAnswer")
@Table(name = "questionnaire_answer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @Column
    private boolean answer;

}
