package eu.dreamTeam.isabackend.service.questionnaire;

import eu.dreamTeam.isabackend.model.Questionnaire;
import eu.dreamTeam.isabackend.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;


    public boolean checkIfUserCompletedQuestionnaire(String userEmail) {
        var questionnaires = questionnaireRepository.findAllByUserEmail(userEmail);
        return questionnaires.size() > 0;
    }

    public Questionnaire create(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }
}
