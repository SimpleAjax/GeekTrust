package com.geektrust.makespace.validators;

import com.geektrust.makespace.exceptions.OverlappingIntervalException;

public class CapacityValidator {

    final int MAX_CAPACITY = 20;
    final int MIN_CAPACITY = 2;

    public void validate(final int capacity) throws OverlappingIntervalException {
        if(capacity<MIN_CAPACITY || capacity>MAX_CAPACITY) throw new OverlappingIntervalException();
    }

}
