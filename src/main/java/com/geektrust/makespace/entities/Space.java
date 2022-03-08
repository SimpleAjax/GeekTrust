package com.geektrust.makespace.entities;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Space {
    private final TreeMap<Integer, MeetingRoom> meetingRoomMap;
    public static final String CCave = "C-Cave";
    public static final String DTower = "D-Tower";
    public static final String GMansion = "G-Mansion";

    public Space() {
        this.meetingRoomMap = new TreeMap<>();
        MeetingRoom cCave = new MeetingRoom("C-Cave", 3);
        MeetingRoom dTower = new MeetingRoom("D-Tower", 7);
        MeetingRoom gMansion = new MeetingRoom("G-Mansion", 20);
        meetingRoomMap.put( cCave.getCapacity(), cCave);
        meetingRoomMap.put(dTower.getCapacity(), dTower);
        meetingRoomMap.put(gMansion.getCapacity(), gMansion);
    }

    public MeetingRoom getNextBiggerRoom(final int capacity) {
        Map.Entry<Integer, MeetingRoom> entry = meetingRoomMap.ceilingEntry(capacity);
        if(entry==null) return null;
        return entry.getValue();
    }

    public List<MeetingRoom> getAvailableRooms(final Interval interval) {
        List<MeetingRoom> availableRooms = new ArrayList<>();
        for(MeetingRoom meetingRoom : meetingRoomMap.values()) {
            if(meetingRoom.isAvailable(interval)) {
                availableRooms.add(meetingRoom);
            }
        }
        return availableRooms;
    }
}
