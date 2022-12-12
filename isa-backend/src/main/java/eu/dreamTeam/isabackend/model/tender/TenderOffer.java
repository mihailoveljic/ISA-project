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
public class TenderOffer {
    @Id
    @JsonProperty("Id")
    private int id;
    @JsonProperty("Email")
    @Column
    private String email;
    @JsonProperty("TenderId")
    private int tenderId;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty("Bids")
    private List<Bid> bids;
    @Column
    @JsonProperty("DeliveryTime")
    private Date deliveryTime;
    @Column
    @JsonProperty("IsAccepted")
    private boolean isAccepted;
    @Column
    @JsonProperty("Price")
    private double price;
}
