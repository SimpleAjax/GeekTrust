package com.geektrust.makespace.entities;

import com.geektrust.makespace.exceptions.IncorrectInputException;
import com.geektrust.makespace.exceptions.TimeFormatException;
import com.geektrust.makespace.util.TimeUtil;

import java.sql.Time;

public class Interval {
    private final Time startTime;
    private final Time endTime;

    public Interval(String startTime, String endTime) {
        this.startTime = TimeUtil.getHHMMFormat(startTime);
        this.endTime = TimeUtil.getHHMMFormat(endTime);
    }

    public boolean isOverlapping(final Interval interval) {
        return !(interval.endTime.before(this.startTime) || interval.endTime.equals(this.startTime) ||
                this.endTime.before(interval.startTime) || this.endTime.equals(interval.startTime));
    }

    public boolean isEndBeforeStart() {
        return endTime.before(startTime);
    }

    public boolean is15MinFormat() {
        return startTime.getMinutes()%15==0 && endTime.getTime()%15==0;
    }
}
