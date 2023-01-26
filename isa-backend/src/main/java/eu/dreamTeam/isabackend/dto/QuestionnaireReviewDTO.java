package eu.dreamTeam.isabackend.dto;

import lombok.Data;

@Data
public class QuestionnaireReviewDTO {
    private String question;
    private boolean acceptableAnswer;
    private boolean answer;
}
