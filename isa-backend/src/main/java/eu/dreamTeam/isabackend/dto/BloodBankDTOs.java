package eu.dreamTeam.isabackend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BloodBankDTOs {
    private List<BloodBankDTO> bloodBanks;

}
