package eu.dreamTeam.isabackend.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuestionnaireCompletedDTO {
    private boolean completed;
}
