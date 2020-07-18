package com.uadan.cab.service.relavance;

import com.uadan.cab.manager.CabManager;
import com.uadan.cab.models.Cab;
import com.uadan.cab.models.Location;
import com.uadan.cab.models.TripStatus;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class AllAvailableCabFinderImpl implements CabFinderService {
    private CabManager cabManager;

    public List<Cab> getCabs(List<Cab> cabs, Location source, Location destination) {
        if (CollectionUtils.isEmpty(cabs)) {
            return null;
        }
        return cabs.stream().filter(cab -> {
            if (cab.getDriver() !=null && cab.getDriver().isAvailable() && cab.getTrip()== null && cab.getTrip().getTripStatus().equals(TripStatus.ENDED)) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
    }
}
