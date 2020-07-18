package com.uadan.cab.manager;

import com.google.common.collect.Maps;
import com.uadan.cab.exceptions.DriverNotAvailable;
import com.uadan.cab.exceptions.UserAlreadyExist;
import com.uadan.cab.models.Driver;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

public class DriverManager {
    private Map<String, Driver> drivers;

    public DriverManager() {
        this.drivers = Maps.newHashMap();
    }

    public boolean register(Driver user) throws UserAlreadyExist {
        if ( MapUtils.isNotEmpty(this.drivers) && drivers.containsKey(user.getId())) {
            throw new UserAlreadyExist("rider already exist as Drvier");
        }
        drivers.put(user.getId(), user);
        return true;
    }

    public boolean setAvailability(String driverId, boolean isAvailable) {

        try {
            if (!drivers.containsKey(driverId)) {
                throw new DriverNotAvailable("driver doesn't exist");
            }
            Driver driver = drivers.get(driverId);
            synchronized (this) {
                driver.checkValidState(driver, isAvailable);
                driver.setAvailable(isAvailable);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean driverExist(Driver driver) throws DriverNotAvailable {
        if (driver == null || drivers.containsKey(driver.getId())) {
            throw new DriverNotAvailable("the driver doesn't exit");
        }
        return true;
    }
}
