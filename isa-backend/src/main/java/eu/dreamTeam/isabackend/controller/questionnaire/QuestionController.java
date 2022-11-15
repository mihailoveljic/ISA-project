package eu.dreamTeam.isabackend.controller.questionnaire;

import eu.dreamTeam.isabackend.dto.BloodBankDTO;
import eu.dreamTeam.isabackend.dto.BloodBankDTOs;
import eu.dreamTeam.isabackend.dto.QuestionDTO;
import eu.dreamTeam.isabackend.dto.QuestionDTOs;
import eu.dreamTeam.isabackend.model.BloodBank;
import eu.dreamTeam.isabackend.model.Question;
import eu.dreamTeam.isabackend.service.questionnaire.QuestionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionsService;
    @Autowired
    private ModelMapper modelMapper;

    public QuestionController(QuestionService questionsService, ModelMapper modelMapper) {
        this.questionsService = questionsService;
        this.modelMapper = modelMapper;
        TypeMap<Question, QuestionDTO> propertyMapper = modelMapper.createTypeMap(Question.class, QuestionDTO.class);
        propertyMapper.addMappings(mapper -> mapper.map(Question::getId, QuestionDTO::setId));
        propertyMapper.addMappings(mapper -> mapper.map(Question::getText, QuestionDTO::setText));
        propertyMapper.addMappings(mapper -> mapper.map(Question::isAcceptableAnswer, QuestionDTO::setAcceptableAnswer));
        propertyMapper.addMappings(mapper -> mapper.map(Question::getForGender, QuestionDTO::setForGender));
    }

    @GetMapping("/get-all")
    public ResponseEntity<QuestionDTOs> getAll(){
        List<Question> questions = this.questionsService.getAll();

        List<QuestionDTO> questionDTOs = questions.stream().map(question -> modelMapper.map(question, QuestionDTO.class)).toList();

        QuestionDTOs response = QuestionDTOs.builder()
                .questions(questionDTOs)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
