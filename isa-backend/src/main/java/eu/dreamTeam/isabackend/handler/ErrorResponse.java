package eu.dreamTeam.isabackend.handler;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String title;

    public ErrorResponse() {
        this.title = "Blood bank application";
    }
}
