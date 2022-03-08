package com.geektrust.makespace.controller;

import com.geektrust.makespace.entities.Space;
import com.geektrust.makespace.requests.BookingRequest;
import com.geektrust.makespace.response.BookingResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MeetingBookingTest {

    SpaceController spaceController;

    @BeforeEach
    public void setup() {
        spaceController = new SpaceController();
    }

    @Test
    public void overlapWithBufferTime() {
        BookingResponse response = spaceController.bookMeetingRoom(new BookingRequest("09:00", "09:30", 3));
        Assertions.assertEquals(response.getResponse(),spaceController.NO_VACANT_ROOM);
    }

    @Test
    public void overlapWithExistingBooking() {
        BookingResponse response = spaceController.bookMeetingRoom(new BookingRequest("09:30", "09:45", 10));
        System.out.println(response.getResponse());
        Assertions.assertEquals(response.getResponse(), Space.GMansion);

        response = spaceController.bookMeetingRoom(new BookingRequest("09:30", "10:00", 10));
        System.out.println(response.getResponse());
        Assertions.assertEquals(response.getResponse(),spaceController.NO_VACANT_ROOM);
    }

    @Test
    public void invalidTimeFormat() {
        BookingResponse response = spaceController.bookMeetingRoom(new BookingRequest("10:60", "11:45", 10));
        System.out.println(response.getResponse());
        Assertions.assertEquals(response.getResponse(), spaceController.INCORRECT_INPUT);
    }

    @Test
    public void startAfterEndTime() {
        BookingResponse response = spaceController.bookMeetingRoom(new BookingRequest("11:45", "10:00", 10));
        System.out.println(response.getResponse());
        Assertions.assertEquals(response.getResponse(), spaceController.INCORRECT_INPUT);
    }
    
    @Test
    public void not15MinFormat() {
        BookingResponse response = spaceController.bookMeetingRoom(new BookingRequest("11:43", "12:00", 10));
        System.out.println(response.getResponse());
        Assertions.assertEquals(response.getResponse(), spaceController.INCORRECT_INPUT);
    }

    @Test
    public void invalidCapacity() {
        BookingResponse response = spaceController.bookMeetingRoom(new BookingRequest("11:45", "12:00", 30));
        System.out.println(response.getResponse());
        Assertions.assertEquals(response.getResponse(), spaceController.NO_VACANT_ROOM);
    }

    @Test
    public void correctCapacityAllocation() {
        BookingResponse response = spaceController.bookMeetingRoom(new BookingRequest("11:00", "12:00", 4));
        System.out.println(response.getResponse());
        Assertions.assertEquals(response.getResponse(), Space.DTower);
    }

    @Test
    public void checkNextBiggerCapacityAllocation() {
        BookingResponse response = spaceController.bookMeetingRoom(new BookingRequest("11:00", "12:00", 4));
        System.out.println(response.getResponse());
        Assertions.assertEquals(response.getResponse(), Space.DTower);

        response = spaceController.bookMeetingRoom(new BookingRequest("11:00", "12:00", 4));
        System.out.println(response.getResponse());
        Assertions.assertEquals(response.getResponse(), Space.GMansion);
    }
}
