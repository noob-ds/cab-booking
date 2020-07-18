package com.uadan.cab.resources;

import com.uadan.cab.manager.CabManager;
import com.uadan.cab.manager.DriverManager;
import com.uadan.cab.manager.RiderManager;
import com.uadan.cab.manager.TripManager;
import com.uadan.cab.models.*;
import com.uadan.cab.service.allocator.CabAllocatorService;
import com.uadan.cab.service.allocator.RandomCabAllocatorService;
import com.uadan.cab.service.pricing.ConstantPrice;
import com.uadan.cab.service.pricing.PriceCalculator;
import com.uadan.cab.service.ranking.CabRankingService;
import com.uadan.cab.service.ranking.RandomCabRankingService;
import com.uadan.cab.service.relavance.AllAvailableCabFinderImpl;
import com.uadan.cab.service.relavance.CabFinderService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


public class CabBookingResources {
    private CabManager cabManager;
    private RiderManager riderManager;
    private TripManager tripManager;
    private DriverManager driverManager;


    public CabBookingResources() {
        this.cabManager = new CabManager();
        this.riderManager = new RiderManager();
        this.driverManager = new DriverManager();
        CabFinderService cabFinderService = new AllAvailableCabFinderImpl();
        CabRankingService cabRankingService = new RandomCabRankingService();
        CabAllocatorService cabAllocatorService = new RandomCabAllocatorService();
        PriceCalculator priceCalculator = new ConstantPrice();
        this.tripManager = new TripManager(cabFinderService, cabRankingService, cabAllocatorService, priceCalculator);
    }

    public boolean registerRider(Rider rider) {
        try {
            return riderManager.register(rider);
        } catch (RuntimeException e) {
            //todo can add reson in the response
            System.out.println("rider" + e.getMessage());

            return true;
        } catch (Exception e) {
            System.out.println("error while registering the rider");

            return false;
        }

    }

    public boolean registerDriver(Driver driver) {
        try {
            return driverManager.register(driver);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //todo can add reson in the response
            return false;
        }
    }

    public boolean registerCab(Cab cab) {
        try {
            driverManager.driverExist(cab.getDriver());
            return cabManager.register(cab);
        } catch (Exception e) {
            //todo can add reson in the response
            System.out.println("cab is not registered the friver" + e.getMessage());

            return false;
        }
    }

    public boolean updateCabLocation(String cabId, Location location) {
        try {
            return cabManager.updateLocation(cabId, location);
        } catch (Exception e) {
            //todo can add reson in the response
            return false;
        }
    }

    public boolean updateDriverAvailability(String driverId, boolean isAvailable) {
        try {
            return driverManager.setAvailability(driverId, isAvailable);
        } catch (Exception e) {
            //todo can add reson in the response
            return false;
        }
    }

    public Trip bookCab(Rider rider, Location source, Location destination) {
        try {
            List<Cab> cabs = cabManager.getAllCabs();
            Trip trip = tripManager.book(cabs, rider, source, destination);
            riderManager.addTrip(trip);
            tripManager.addTrip(trip);
            return trip;
        } catch (Exception e) {
            //todo can add reson in the response
            return null;
        }
    }

    public List<Trip> riderTrip(Rider rider) {
        try {
            return riderManager.getTrip(rider);
        } catch (Exception e) {
            //todo can add reson in the response
            return null;
        }
    }

    public boolean updateCabStatus(Trip trip, TripStatus status) {
        try {
            return tripManager.updateStatus(trip, status);
        } catch (Exception e) {
            //todo can add reson in the response
            return false;
        }
    }
}
