package com.geektrust.makespace.controller;

import com.geektrust.makespace.entities.MeetingRoom;
import com.geektrust.makespace.exceptions.IncorrectInputException;
import com.geektrust.makespace.exceptions.OverlappingIntervalException;
import com.geektrust.makespace.requests.AvailabilityRequest;
import com.geektrust.makespace.requests.BookingRequest;
import com.geektrust.makespace.response.AvailabilityResponse;
import com.geektrust.makespace.response.BookingResponse;
import com.geektrust.makespace.services.ISpaceService;
import com.geektrust.makespace.services.impl.SpaceService;
import com.geektrust.makespace.validators.CapacityValidator;
import com.geektrust.makespace.validators.IntervalValidator;
import com.geektrust.makespace.validators.BufferOverlapValidator;

import java.util.List;

public class SpaceController {
    ISpaceService spaceService;
    public final String NO_VACANT_ROOM = "NO_VACANT_ROOM";
    public final String INCORRECT_INPUT = "INCORRECT_INPUT";
    public final String AVAILABLE = "AVAILABLE";

    public SpaceController() {
        this.spaceService = new SpaceService();
    }

    public synchronized BookingResponse bookMeetingRoom(BookingRequest bookingRequest) {
        try {
            new IntervalValidator().validate(bookingRequest.getStartTime(), bookingRequest.getEndTime());
        } catch (IncorrectInputException e) {
            return new BookingResponse(INCORRECT_INPUT);
        }

        try {
            new BufferOverlapValidator().validate(bookingRequest.getInterval());
        } catch (OverlappingIntervalException e) {
            return new BookingResponse(NO_VACANT_ROOM);
        }

        MeetingRoom meetingRoom = spaceService.getNextBiggerAvailableRoom(bookingRequest.getCapacity(),
                bookingRequest.getInterval());
        if(meetingRoom==null) return new BookingResponse(NO_VACANT_ROOM);
        spaceService.createBooking(meetingRoom, bookingRequest.getInterval());
        return new BookingResponse(meetingRoom.getName());
    }

    public AvailabilityResponse viewAvailability(AvailabilityRequest availabilityRequest) {
        try {
            new IntervalValidator().validate(availabilityRequest.getStartTime(), availabilityRequest.getEndTime());
        } catch (IncorrectInputException e) {
            return new AvailabilityResponse(INCORRECT_INPUT, null);
        }

        try {
            new BufferOverlapValidator().validate(availabilityRequest.getInterval());
        } catch (OverlappingIntervalException e) {
            return new AvailabilityResponse(NO_VACANT_ROOM, null);
        }

        List<String> availableRooms = spaceService.getAvailableRooms(availabilityRequest.getInterval());
        if(availableRooms==null || availableRooms.isEmpty()) {
            return new AvailabilityResponse(NO_VACANT_ROOM, null);
        }
        return new AvailabilityResponse(AVAILABLE, availableRooms);
    }

}
