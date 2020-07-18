package com.uadan.cab.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Driver extends User {
    private String id;

    public Driver(String id) {
        this.id = id;
    }

    @Setter
    private boolean isAvailable = true;
    private static final UserType type = UserType.DRIVER;

    public String getId() {
        return null;
    }

    public UserType getType() {
        return null;
    }

    public void checkValidState(Driver driver, boolean isAvailable) {
        //todo throw exception if not a valid state
    }
}
