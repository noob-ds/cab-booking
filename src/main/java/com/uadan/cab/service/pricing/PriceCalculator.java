package com.uadan.cab.service.pricing;

import com.uadan.cab.models.Trip;

public interface PriceCalculator {
    public Double price(Trip trip);
}
