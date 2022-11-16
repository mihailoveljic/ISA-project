package eu.dreamTeam.isabackend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionnaireAnswerDTO {
    private long id;
    private QuestionDTO question;
    private boolean answer;
}
