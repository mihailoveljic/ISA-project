package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository  extends JpaRepository<Answer, Long> {
}
