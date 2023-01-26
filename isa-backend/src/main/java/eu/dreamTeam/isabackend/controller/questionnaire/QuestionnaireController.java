package eu.dreamTeam.isabackend.controller.questionnaire;

import eu.dreamTeam.isabackend.dto.*;
import eu.dreamTeam.isabackend.model.*;
import eu.dreamTeam.isabackend.service.UserService;
import eu.dreamTeam.isabackend.service.questionnaire.QuestionService;
import eu.dreamTeam.isabackend.service.questionnaire.QuestionnaireService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('user', 'staff')")
    @GetMapping("/check-completed/{userEmail}")
    public ResponseEntity<Object> checkIfUserCompletedQuestionnaire(@PathVariable String userEmail){
        QuestionnaireCompletedDTO response = QuestionnaireCompletedDTO.builder()
                .completed(questionnaireService.checkIfUserCompletedQuestionnaire(userEmail))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('user', 'staff')")
    @GetMapping("/check")
    public ResponseEntity<StringDTO> check(@RequestParam String email){
        StringDTO string = new StringDTO();
        if(!questionnaireService.check(email)) {
            string.setText("The user does not meet the requirements for donating blood");
            return new ResponseEntity<>(string, HttpStatus.OK);
        }
        return new ResponseEntity<>(string, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('user', 'staff')")
    @GetMapping("/{email}")
    public ResponseEntity<List<QuestionnaireReviewDTO>> getQuestionnaireByUser(@PathVariable String email){
        return new ResponseEntity<>(questionnaireService.getQuestionnaireByUser(email), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('user', 'staff')")
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
                    .build();

            questionnaireAnswers.add(questionnaireAnswer);
        }

        questionnaire.setQuestionnaireAnswers(questionnaireAnswers);
        var response = questionnaireService.create(questionnaire);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
