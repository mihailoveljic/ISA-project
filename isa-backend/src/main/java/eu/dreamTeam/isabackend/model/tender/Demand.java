package eu.dreamTeam.isabackend.model.tender;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Demand {
    @Id
    @JsonProperty("Id")
    private int id;
    @Embedded
    @JsonProperty("BloodRequest")
    private Blood bloodRequest;
}
