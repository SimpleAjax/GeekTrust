package com.geektrust.makespace.services.impl;

import com.geektrust.makespace.entities.Booking;
import com.geektrust.makespace.entities.Interval;
import com.geektrust.makespace.entities.MeetingRoom;
import com.geektrust.makespace.entities.Space;
import com.geektrust.makespace.services.ISpaceService;

import java.util.ArrayList;
import java.util.List;

public class SpaceService implements ISpaceService {

    private final Space space;
    public SpaceService() {
        space = new Space();
    }

    @Override
    public MeetingRoom getNextBiggerAvailableRoom(int capacity, Interval interval) {
        MeetingRoom meetingRoom = space.getNextBiggerRoom(capacity);
        while(meetingRoom!=null && !meetingRoom.isAvailable(interval)) {
            capacity = meetingRoom.getCapacity()+1;
            meetingRoom = space.getNextBiggerRoom(capacity);
        }
        return meetingRoom;
    }

    @Override
    public void createBooking(MeetingRoom meetingRoom, Interval interval) {
        Booking booking = new Booking(interval);
        meetingRoom.addBooking(booking);
    }

    @Override
    public List<String> getAvailableRooms(Interval interval) {
        List<MeetingRoom> meetingRooms =  space.getAvailableRooms(interval);
        List<String> roomNameList = new ArrayList<>();
        for(MeetingRoom meetingRoom : meetingRooms) {
            roomNameList.add(meetingRoom.getName());
        }
        return roomNameList;
    }
}
