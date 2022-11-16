package eu.dreamTeam.isabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireDTO {
    private long id;
    private String title;
    private String userEmail;
    private List<QuestionnaireAnswerDTO> questionnaireAnswers;
}
