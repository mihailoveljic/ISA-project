package eu.dreamTeam.isabackend.service.questionnaire;

import eu.dreamTeam.isabackend.model.Questionnaire;
import eu.dreamTeam.isabackend.model.QuestionnaireAnswer;
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

    public boolean check(String email) {
        Questionnaire questionnaire = !questionnaireRepository.findAllByUserEmail(email).isEmpty() ? questionnaireRepository.findAllByUserEmail(email).get(0):null;
        if (questionnaire == null)
            return false;
        for(QuestionnaireAnswer qa : questionnaire.getQuestionnaireAnswers()){
            if(qa.isAnswer())
                return false;
        }
        return true;
    }
    public Questionnaire create(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }
}
