package com.geektrust.makespace.response;

import com.geektrust.makespace.entities.MeetingRoom;

import java.util.List;

public class AvailabilityResponse {
    private final List<String> meetingRooms;
    private final String message;

    public AvailabilityResponse(String message, List<String> meetingRooms) {
        this.message =message;
        this.meetingRooms = meetingRooms;
    }

    public List<String> getMeetingRooms() {
        return meetingRooms;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "AvailabilityResponse{" +
                "meetingRooms=" + meetingRooms +
                ", message='" + message + '\'' +
                '}';
    }
}
