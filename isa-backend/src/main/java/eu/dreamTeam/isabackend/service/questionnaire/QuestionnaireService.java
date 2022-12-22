package eu.dreamTeam.isabackend.service.questionnaire;

import eu.dreamTeam.isabackend.dto.QuestionnaireAnswerDTO;
import eu.dreamTeam.isabackend.dto.QuestionnaireReviewDTO;
import eu.dreamTeam.isabackend.model.Questionnaire;
import eu.dreamTeam.isabackend.model.QuestionnaireAnswer;
import eu.dreamTeam.isabackend.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;


    public boolean checkIfUserCompletedQuestionnaire(String userEmail) {
        var questionnaires = questionnaireRepository.findAllByUserEmail(userEmail);
        return questionnaires.size() > 0;
    }

    public List<QuestionnaireReviewDTO> getQuestionnaireByUser(String email) {
        List<QuestionnaireReviewDTO> questionnaireReviewDTOS = new ArrayList<>();
        for(QuestionnaireAnswer qa : questionnaireRepository.findAllByUserEmail(email).get(0).getQuestionnaireAnswers()){
            QuestionnaireReviewDTO questionnaireReviewDTO = new QuestionnaireReviewDTO();
            questionnaireReviewDTO.setQuestion(qa.getQuestion().getText());
            questionnaireReviewDTO.setAcceptableAnswer(qa.getQuestion().isAcceptableAnswer());
            questionnaireReviewDTO.setAnswer(qa.isAnswer());
            questionnaireReviewDTOS.add(questionnaireReviewDTO);
        }
        return questionnaireReviewDTOS;
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
