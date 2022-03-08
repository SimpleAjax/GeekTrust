package com.geektrust.makespace.controller;

import com.geektrust.makespace.entities.Space;
import com.geektrust.makespace.requests.AvailabilityRequest;
import com.geektrust.makespace.requests.BookingRequest;
import com.geektrust.makespace.response.AvailabilityResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RoomAvailabilityTest {
    SpaceController spaceController;

    @BeforeEach
    public void setup() {
        spaceController = new SpaceController();
    }

    @Test
    public void checkAvailabilityForNoVacantRoom() {
        AvailabilityResponse response = spaceController.viewAvailability(new AvailabilityRequest("09:00", "10:00"));
        Assertions.assertEquals(response.getMessage(), spaceController.NO_VACANT_ROOM);
    }

    @Test
    public void checkAvailabilityWhenOneRoomBooked() {
        spaceController.bookMeetingRoom(new BookingRequest("04:00", "05:00", 3));
        AvailabilityResponse response = spaceController.viewAvailability(new AvailabilityRequest("04:30", "05:30"));
        Assertions.assertEquals(response.getMessage(), spaceController.AVAILABLE);
        List<String> availableRooms = response.getMeetingRooms();
        Assertions.assertEquals(availableRooms.size(), 2);
        Assertions.assertTrue(availableRooms.contains(Space.DTower));
        Assertions.assertTrue(availableRooms.contains(Space.GMansion));
    }

    @Test
    public void checkAvailabilityWhenTwoRoomBooked() {
        spaceController.bookMeetingRoom(new BookingRequest("04:00", "05:00", 3));
        spaceController.bookMeetingRoom(new BookingRequest("04:30", "05:30", 3));
        AvailabilityResponse response = spaceController.viewAvailability(new AvailabilityRequest("04:45", "05:00"));
        Assertions.assertEquals(response.getMessage(), spaceController.AVAILABLE);
        List<String> availableRooms = response.getMeetingRooms();
        Assertions.assertEquals(availableRooms.size(), 1);
        Assertions.assertTrue(availableRooms.contains(Space.GMansion));
    }


    @Test
    public void checkAvailabilityWhenAllRoomsBooked() {
        spaceController.bookMeetingRoom(new BookingRequest("04:00", "05:00", 3));
        spaceController.bookMeetingRoom(new BookingRequest("04:30", "05:30", 3));
        spaceController.bookMeetingRoom(new BookingRequest("04:30", "06:30", 3));
        AvailabilityResponse response = spaceController.viewAvailability(new AvailabilityRequest("04:45", "05:00"));
        Assertions.assertEquals(response.getMessage(), spaceController.NO_VACANT_ROOM);
    }

    @Test
    public void invalidTimeFormat() {
        AvailabilityResponse response = spaceController.viewAvailability(new AvailabilityRequest("10:60", "11:45"));
        Assertions.assertEquals(response.getMessage(), spaceController.INCORRECT_INPUT);
    }

    @Test
    public void startAfterEndTime() {
        AvailabilityResponse response = spaceController.viewAvailability(new AvailabilityRequest("11:45", "10:00"));
        Assertions.assertEquals(response.getMessage(), spaceController.INCORRECT_INPUT);
    }

    @Test
    public void not15MinFormat() {
        AvailabilityResponse response = spaceController.viewAvailability(new AvailabilityRequest("11:43", "12:00"));
        Assertions.assertEquals(response.getMessage(), spaceController.INCORRECT_INPUT);
    }
}
