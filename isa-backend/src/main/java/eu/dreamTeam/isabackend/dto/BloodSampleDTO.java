package eu.dreamTeam.isabackend.dto;

import eu.dreamTeam.isabackend.model.enums.BloodType;
import lombok.Data;

@Data
public class BloodSampleDTO {

    private BloodType bloodType;
    private double amount;
}
