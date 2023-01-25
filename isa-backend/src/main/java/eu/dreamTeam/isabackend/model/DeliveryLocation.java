package eu.dreamTeam.isabackend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryLocation {
    @Id
    private long id;
    private double latitude;
    private double longitude;
    private double startLatitude;
    private double startLongitude;
    private double endLatitude;
    private double endLongitude;
    private boolean delivered;
}
