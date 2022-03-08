package com.geektrust.makespace.entities;

import java.util.ArrayList;
import java.util.List;

public class MeetingRoom {
    private final String name;
    private final int capacity;
    private final List<Booking> bookings;

    public MeetingRoom(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        bookings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public boolean isAvailable(Interval interval) {
        for(Booking booking : bookings) {
            if(booking.isOverLapping(interval)) return false;
        }
        return true;
    }
}
