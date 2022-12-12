package eu.dreamTeam.isabackend.dto.message;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TenderOfferConfirmationMessage {
    private int tenderOfferId;
    private int tenderId;
    private boolean confirmed;
}
