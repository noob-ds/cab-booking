package com.uadan.cab;

import com.uadan.cab.models.Cab;
import com.uadan.cab.models.Driver;
import com.uadan.cab.models.Rider;
import com.uadan.cab.resources.CabBookingResources;

public class CabApplication {

    public static void main(String[] args) {
        CabBookingResources cabBookingResources = new CabBookingResources();
        Driver driver1 = createDriver("D1");
        Driver driver2 = createDriver("D2");
        Rider rider = createRider("R2");
        Cab cab1 = createCab("C1", driver1, Cab.CabType.NORMAL);
        Cab cab2 = createCab("C2", driver2, Cab.CabType.NORMAL);

        System.out.println( cabBookingResources.registerDriver(driver1));
        System.out.println( cabBookingResources.registerRider(rider));
        System.out.println( cabBookingResources.registerCab(cab1));
        System.out.println( cabBookingResources.registerCab(cab2));
        System.out.println( cabBookingResources.registerDriver(driver2));
        System.out.println( cabBookingResources.registerCab(cab2));


    }

    private static Cab createCab(String id, Driver driver, Cab.CabType cabType) {
        return new Cab(id, driver, cabType);
    }

    private static Rider createRider(String rider) {
        return new Rider(rider);
    }

    private static Driver createDriver(String driver) {
        return new Driver(driver);
    }

}
