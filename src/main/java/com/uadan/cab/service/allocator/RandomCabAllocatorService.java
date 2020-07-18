package com.uadan.cab.service.allocator;

import com.uadan.cab.models.Cab;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class RandomCabAllocatorService implements CabAllocatorService {
    @Override
    public Cab getCab(List<Cab> cabs, Object obj) {
        if (CollectionUtils.isEmpty(cabs)) {
            return null;
        }
        return cabs.get(0);
    }
}
