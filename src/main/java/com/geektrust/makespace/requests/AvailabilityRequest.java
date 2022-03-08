package com.geektrust.makespace.requests;

import com.geektrust.makespace.entities.Interval;

public class AvailabilityRequest {
    private final String startTime;
    private final String endTime;

    public AvailabilityRequest(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Interval getInterval() {
        return new Interval(startTime, endTime);
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
