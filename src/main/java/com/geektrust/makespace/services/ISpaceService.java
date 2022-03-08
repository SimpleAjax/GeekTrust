package com.geektrust.makespace.services;

import com.geektrust.makespace.entities.Interval;
import com.geektrust.makespace.entities.MeetingRoom;

import java.util.List;

public interface ISpaceService {
    MeetingRoom getNextBiggerAvailableRoom(int capacity, Interval interval);

    void createBooking(MeetingRoom meetingRoom, Interval interval);

    List<String> getAvailableRooms(Interval interval);
}
