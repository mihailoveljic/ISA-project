package eu.dreamTeam.isabackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateStaffDTO{
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
}
