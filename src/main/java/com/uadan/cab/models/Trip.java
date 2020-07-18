package com.uadan.cab.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class Trip {
    private String id;
    private Rider rider;
    private Cab cab;
    @Setter private TripStatus tripStatus;
    private Location source;
    private Location destination;
    @Setter private Double price;
}
