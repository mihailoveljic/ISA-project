package eu.dreamTeam.isabackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdatePasswordDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String newPassword;
}
