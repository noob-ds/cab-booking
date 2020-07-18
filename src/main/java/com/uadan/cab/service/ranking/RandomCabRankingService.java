package com.uadan.cab.service.ranking;

import com.uadan.cab.context.RiderContext;
import com.uadan.cab.models.Cab;

import java.util.List;

public class RandomCabRankingService implements CabRankingService {

    @Override
    public List<Cab> get(List<Cab> cabs, RiderContext riderContext) {
        return cabs;
    }
}
