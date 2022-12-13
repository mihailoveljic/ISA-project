package eu.dreamTeam.isabackend.model.tender;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bid {
    @Id
    @JsonProperty("Id")
    private int id;
    @Column
    @JsonProperty("DemandId")
    private int demandId;
    @Embedded
    @JsonProperty("BloodRequest")
    private Blood bloodRequest;
}
