package com.uadan.cab.service.relavance;

import com.uadan.cab.models.Cab;
import com.uadan.cab.models.Location;

import java.util.List;

public interface CabFinderService {
    List<Cab> getCabs(List<Cab>cab , Location source, Location destination);
}
