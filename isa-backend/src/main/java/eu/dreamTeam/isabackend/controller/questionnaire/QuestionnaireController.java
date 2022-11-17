package eu.dreamTeam.isabackend.controller.questionnaire;

import eu.dreamTeam.isabackend.dto.BloodBankDTO;
import eu.dreamTeam.isabackend.dto.QuestionnaireAnswerDTO;
import eu.dreamTeam.isabackend.dto.QuestionnaireCompletedDTO;
import eu.dreamTeam.isabackend.dto.QuestionnaireDTO;
import eu.dreamTeam.isabackend.model.*;
import eu.dreamTeam.isabackend.service.UserService;
import eu.dreamTeam.isabackend.service.questionnaire.QuestionService;
import eu.dreamTeam.isabackend.service.questionnaire.QuestionnaireService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/questionnaire")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/check-completed/{userEmail}")
    public ResponseEntity<Object> checkIfUserCompletedQuestionnaire(@PathVariable String userEmail){
        QuestionnaireCompletedDTO response = QuestionnaireCompletedDTO.builder()
                .completed(questionnaireService.checkIfUserCompletedQuestionnaire(userEmail))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody QuestionnaireDTO questionnaireDTO){
        User user = userService.getUserByEmail(questionnaireDTO.getUserEmail());

        Questionnaire questionnaire = Questionnaire.builder()
                        .title(questionnaireDTO.getTitle())
                        .user(user)
                    .build();

        List<QuestionnaireAnswer> questionnaireAnswers = new ArrayList<>();
        for(var questionnaireAnswerDTO : questionnaireDTO.getQuestionnaireAnswers()){

            var question = questionService.getById(questionnaireAnswerDTO.getQuestion().getId());

            var questionnaireAnswer = QuestionnaireAnswer.builder()
                            .question(question)
                            .answer(questionnaireAnswerDTO.isAnswer())
                            .questionnaire(questionnaire).build();

            questionnaireAnswers.add(questionnaireAnswer);
        }

        questionnaire.setQuestionnaireAnswers(questionnaireAnswers);
        var response = questionnaireService.create(questionnaire);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}