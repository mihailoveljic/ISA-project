package eu.dreamTeam.isabackend.dto;

import eu.dreamTeam.isabackend.model.enums.BloodType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SampleDTO {
    @NotNull
    private Long id;
    @NotBlank
    private BloodType bloodType;
    @NotBlank
    private double amount;
}
