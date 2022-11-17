package eu.dreamTeam.isabackend.service.questionnaire;

import eu.dreamTeam.isabackend.model.Question;
import eu.dreamTeam.isabackend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAll(){
        return questionRepository.findAll();
    }

    public Question getById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }
}
