package eu.dreamTeam.isabackend.model.tender;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.dreamTeam.isabackend.model.enums.BloodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Blood {
    @Enumerated(EnumType.STRING)
    @JsonProperty("BloodType")
    private BloodType bloodType;
    @Column
    @JsonProperty("Quantity")
    private double quantity;
}
