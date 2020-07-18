package com.uadan.cab.manager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.uadan.cab.exceptions.CabAlreadyExist;
import com.uadan.cab.exceptions.CabDoesNotExist;
import com.uadan.cab.models.Cab;
import com.uadan.cab.models.Location;
import com.uadan.cab.models.Trip;
import com.uadan.cab.models.TripStatus;

import java.util.List;
import java.util.Map;

public class CabManager {

    private Map<String, Cab> cabs;

    public CabManager() {
        this.cabs = Maps.newHashMap();
    }

    public boolean register(Cab cab) throws CabAlreadyExist {
        if (cabs.containsKey(cab.getId())) {
            throw new CabAlreadyExist("cab already exist");
        }

        cabs.put(cab.getId(), cab);
        return true;
    }

    public boolean updateLocation(String cabId, Location location) throws CabDoesNotExist {
        try {
            if (!cabs.containsKey(cabId)) {
                throw new CabDoesNotExist("cab doesn't exist");
            }
            synchronized (this) {
                Cab cab = cabs.get(cabId);
                cab.setLocation(location);
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    public List<Cab> getAllCabs() {
        return Lists.newArrayList(cabs.values());
    }
}
