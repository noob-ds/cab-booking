package com.uadan.cab.models;

import lombok.Getter;
import lombok.Setter;

@Getter

public class Cab {
    public static enum CabType {
        NORMAL;
    }

    public Cab(String id, Driver driver, CabType cabType) {
        this.id = id;
        this.driver = driver;
        this.cabType = cabType;
    }

    private String id;
    private Driver driver;
    private Trip trip;
    @Setter
    private Location location;
    private CabType cabType;
}

