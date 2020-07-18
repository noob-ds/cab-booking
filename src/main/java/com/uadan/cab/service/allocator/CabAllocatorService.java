package com.uadan.cab.service.allocator;

import com.uadan.cab.models.Cab;

import java.util.List;

public interface CabAllocatorService {
    //todo add context
    Cab getCab(List<Cab> cabs, Object obj);
}
