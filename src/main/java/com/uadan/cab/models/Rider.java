package com.uadan.cab.models;

public class Rider extends User {
    private String id;
    private static final UserType type = UserType.RIDER;

    public Rider(String id) {
        this.id = id;
    }

    public String getId() {
        return null;
    }

    public UserType getType() {
        return type;
    }
}
