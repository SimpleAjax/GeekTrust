package com.geektrust.makespace.entities;

import java.util.UUID;

public class Booking {
    private final UUID bookingId;
    private final Interval interval;

    public Booking(Interval interval) {
        this.bookingId = UUID.randomUUID();
        this.interval = interval;
    }

    public boolean isOverLapping(final Interval interval) {
        return this.interval.isOverlapping(interval);
    }
}
