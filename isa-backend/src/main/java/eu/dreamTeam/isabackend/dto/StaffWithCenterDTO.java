package eu.dreamTeam.isabackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class StaffWithCenterDTO {
    @NotNull
    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    private String jmbg;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String gender;
    @NotBlank
    private String profession;
    @NotBlank
    private String professionInfo;
    @NotBlank
    private String street;
    @NotBlank
    private String number;
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    @NotNull
    private Long bloodBankId;
}
