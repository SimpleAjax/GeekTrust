package com.geektrust.makespace.requests;

import com.geektrust.makespace.entities.Interval;

public class BookingRequest {
    private final String startTime;
    private final String endTime;
    private final int capacity;

    public BookingRequest(String startTime, String endTime, int capacity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public Interval getInterval() {
        return new Interval(startTime, endTime);
    }
}
