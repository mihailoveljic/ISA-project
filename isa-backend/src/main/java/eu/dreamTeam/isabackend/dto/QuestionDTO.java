package eu.dreamTeam.isabackend.dto;

import eu.dreamTeam.isabackend.model.enums.Gender;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDTO {
    private Long id;
    private String text;
    private boolean acceptableAnswer;
    private Gender forGender;
}
