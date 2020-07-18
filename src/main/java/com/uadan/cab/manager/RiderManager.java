package com.uadan.cab.manager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.uadan.cab.exceptions.UserAlreadyExist;
import com.uadan.cab.models.Rider;
import com.uadan.cab.models.Trip;

import java.util.List;
import java.util.Map;


public class RiderManager {
    private Map<String, Rider> riders;
    private Map<String, List<Trip>> userTrips;

    public RiderManager() {
        this.riders =Maps.newHashMap();
        this.userTrips = Maps.newHashMap();
    }

    public boolean register(Rider rider) throws UserAlreadyExist {
        if (riders.containsKey(rider.getId())) {
            throw new UserAlreadyExist("rider already exist");
        }
        riders.put(rider.getId(), rider);
        return true;
    }

    public void addTrip(Trip trip) {
        try {
            if (userTrips.containsKey(trip.getRider().getId())) {
                List<Trip> trips = userTrips.get(trip.getRider().getId());
                if (trips != null) {
                    trips.add(trip);
                } else {
                    trips = Lists.newArrayList(trip);
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public List<Trip> getTrip(Rider rider) {
        return userTrips.getOrDefault(rider.getId(), null);
    }
}
