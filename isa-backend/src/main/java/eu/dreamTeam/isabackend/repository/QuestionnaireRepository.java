package eu.dreamTeam.isabackend.repository;

import eu.dreamTeam.isabackend.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuestionnaireRepository  extends JpaRepository<Questionnaire, Long> {
    @Query(value = "SELECT * FROM public.questionnaire " +
            "INNER JOIN public.users ON users.id = questionnaire.user_id " +
            "INNER JOIN public.account ON account.id = users.account_id " +
            "WHERE account.email LIKE ?1", nativeQuery = true)
    List<Questionnaire> findAllByUserEmail(String userEmail);
}
