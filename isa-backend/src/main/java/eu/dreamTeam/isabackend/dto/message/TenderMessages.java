package eu.dreamTeam.isabackend.dto.message;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TenderMessages {
    private List<TenderMessage> tenderMessages;

    public TenderMessages(List<TenderMessage> tenderMessages) {
        this.tenderMessages = tenderMessages;
    }

    public TenderMessages() {
        this.tenderMessages = new ArrayList<>();
    }
}
