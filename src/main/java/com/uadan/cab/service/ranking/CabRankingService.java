package com.uadan.cab.service.ranking;

import com.uadan.cab.context.RiderContext;
import com.uadan.cab.models.Cab;

import java.util.List;

public interface CabRankingService {
    public List<Cab> get(List<Cab> cab, RiderContext riderContext);
}
