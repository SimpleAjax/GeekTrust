package com.geektrust.makespace;

import com.geektrust.makespace.controller.SpaceController;
import com.geektrust.makespace.requests.AvailabilityRequest;
import com.geektrust.makespace.requests.BookingRequest;
import com.geektrust.makespace.response.AvailabilityResponse;
import com.geektrust.makespace.response.BookingResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**

 APIs:
 1. Booking bookMeetingRoom(startTime, endTime, capacity)
        Booking -> ( BookingStatus, MeetingRoom)
        BookingStatus -> [NO_VACANT_ROOM, INCORRECT_INPUT, CONFIRMED]


 2. List<MeetingRoom> viewAvailability(startTime, endTime)
        MeetingRoom -> (Location, Capacity, Booking[] <- sorted)

 3. Validators:
        InputValidators:
            TimeFormatValidator()
            TimeValidator()
            CapacityValidator()
            SameDayMeetingValidator()
        RoomAvailabilityValidators:
            BufferOverLapValidator()
            BookingOverlapValidator()

 4. RoomBookingStrategies:
        NextBigRoomStrategy()
 */

public class MakeSpace {

    private static final String BOOK_COMMAND = "BOOK";
    private static final String VACANCY_COMMAND = "VACANCY";

    public static void main(String[] args) {
        String filePath = args[0];
        //System.out.println("File path is: " + filePath);
        File file = new File(filePath);
        SpaceController spaceController = new SpaceController();
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line==null || line.isEmpty()) continue;
                String[] command = line.split(" ");

                switch (command[0]) {
                    case BOOK_COMMAND:
                        BookingResponse bookingResponse = spaceController.bookMeetingRoom(new BookingRequest(command[1], command[2],
                                Integer.parseInt(command[3])));
                        System.out.println(bookingResponse.getResponse());
                        break;
                    case VACANCY_COMMAND:
                        AvailabilityResponse availabilityResponse = spaceController.viewAvailability(
                                new AvailabilityRequest(command[1], command[2]));
                        if(availabilityResponse.getMessage().equals(spaceController.AVAILABLE)) {
                            System.out.println(String.join(" ", availabilityResponse.getMeetingRooms()));
                        } else {
                            System.out.println(availabilityResponse.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Invalid Command: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Exception for file: " + filePath);
        }

    }
}
