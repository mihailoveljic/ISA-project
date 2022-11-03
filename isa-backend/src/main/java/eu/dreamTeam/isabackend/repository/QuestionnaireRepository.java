package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository  extends JpaRepository<Questionnaire, Long> {
}
