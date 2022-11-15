package eu.dreamTeam.isabackend.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "QuestionnaireAnswer")
@Table(name = "questionnaire_answer")
public class QuestionnaireAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @Column
    private boolean answer;

}
