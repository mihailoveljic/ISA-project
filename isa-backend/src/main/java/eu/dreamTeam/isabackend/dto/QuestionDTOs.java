package eu.dreamTeam.isabackend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDTOs {
    private List<QuestionDTO> questions;
}
