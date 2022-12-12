package eu.dreamTeam.isabackend.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.dreamTeam.isabackend.model.tender.Tender;
import eu.dreamTeam.isabackend.model.tender.TenderOffer;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TenderMessage {
    @JsonProperty("AcceptedTenderOffer")
    private TenderOffer acceptedTenderOffer;
    @JsonProperty("Tender")
    private Tender tender;
}
