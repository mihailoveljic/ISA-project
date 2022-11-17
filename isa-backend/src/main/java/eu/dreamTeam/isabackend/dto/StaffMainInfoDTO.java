package eu.dreamTeam.isabackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StaffMainInfoDTO {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String profession;
}
