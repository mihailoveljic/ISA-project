package eu.dreamTeam.isabackend.model.tender;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tender {
    @Id
    @JsonProperty("Id")
    private int id;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty("Demands")
    private List<Demand> demands;
    @Column
    @JsonProperty("FromDate")
    private Date fromDate;
    @Column
    @JsonProperty("ToDate")
    private Date toDate;
    @Column
    @JsonProperty("IsActive")
    private boolean isActive;
}
