package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository  extends JpaRepository<Question, Long> {
}
