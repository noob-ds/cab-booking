package com.uadan.cab.manager;

import com.google.common.collect.Maps;
import com.uadan.cab.models.*;
import com.uadan.cab.service.allocator.CabAllocatorService;
import com.uadan.cab.service.pricing.PriceCalculator;
import com.uadan.cab.service.ranking.CabRankingService;
import com.uadan.cab.service.relavance.CabFinderService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TripManager {
    Map<String, Trip> trips;
    private CabFinderService cabFinderService;
    private CabRankingService cabRankingService;
    private CabAllocatorService cabAllocatorService;
    private PriceCalculator priceCalculator;

    public TripManager(CabFinderService cabFinderService, CabRankingService cabRankingService,
                       CabAllocatorService cabAllocatorService, PriceCalculator priceCalculator) {
        this.cabFinderService = cabFinderService;
        this.cabRankingService = cabRankingService;
        this.cabAllocatorService = cabAllocatorService;
        this.priceCalculator = priceCalculator;
        this.trips = Maps.newHashMap();
    }

    public Trip book(List<Cab> cab, Rider rider, Location source, Location destination) throws Exception {
        //get all relevant cabs
        List<Cab> cabs = cabFinderService.getCabs(cab, source, destination);

        List<Cab> rankedCabs = cabRankingService.get(cabs, null);

        Cab allocatedCab = cabAllocatorService.getCab(rankedCabs, null);


        if (allocatedCab != null) {
            Trip trip = Trip.builder()
                    .id(UUID.randomUUID().toString())
                    .cab(allocatedCab)
                    .rider(rider)
                    .destination(destination)
                    .source(source)
                    .tripStatus(TripStatus.ON_GOING)
                    .build();

            trip.setPrice(priceCalculator.price(trip));
            return trip;
        }
        throw new Exception("No cab Available");
    }

    public boolean updateStatus(Trip trip, TripStatus status) {
        try {
            if (trips.containsKey(trip.getId())) {
                Trip trip1 = trips.get(trip.getId());
                trip1.setTripStatus(status);
            }
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    public void addTrip(Trip trip) {
        trips.put(trip.getId(), trip);
    }

}
