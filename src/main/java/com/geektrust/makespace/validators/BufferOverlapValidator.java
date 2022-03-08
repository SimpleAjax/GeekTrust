package com.geektrust.makespace.validators;

import com.geektrust.makespace.entities.Booking;
import com.geektrust.makespace.entities.Interval;
import com.geektrust.makespace.entities.MeetingRoom;
import com.geektrust.makespace.exceptions.OverlappingIntervalException;

import java.util.ArrayList;
import java.util.List;

public class BufferOverlapValidator {

    private final List<Interval> bufferIntervals;
    public BufferOverlapValidator() {
        this.bufferIntervals = new ArrayList<>();
        bufferIntervals.add(new Interval("09:00", "09:15"));
        bufferIntervals.add(new Interval("13:15", "13:45"));
        bufferIntervals.add(new Interval("18:45", "19:00"));
    }

    public void validate(final Interval interval) throws OverlappingIntervalException {
        if(isBufferOverlapFound(interval, bufferIntervals)) throw new OverlappingIntervalException();
    }

    private boolean isBufferOverlapFound(final Interval interval, final List<Interval> intervalList) {
        for(Interval intervalItr : intervalList) {
            if(interval.isOverlapping(intervalItr)) return true;
        }
        return false;
    }
}
