package com.uadan.cab.service.pricing;

import com.uadan.cab.models.Trip;

public class ConstantPrice implements PriceCalculator{
    @Override
    public Double price(Trip trip) {
        return 30d;
    }
}
