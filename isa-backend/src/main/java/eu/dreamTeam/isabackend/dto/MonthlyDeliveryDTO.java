package eu.dreamTeam.isabackend.dto;

import eu.dreamTeam.isabackend.model.QuestionnaireAnswer;
import lombok.Data;

import java.util.List;
@Data
public class MonthlyDeliveryDTO {

    private List<BloodSampleDTO> bloodSamples;
    private String message;
}
