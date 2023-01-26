package com.dreamteam.simulatorlokacija.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class LatLng {
    public double latitude;
    public double longitude;
}
