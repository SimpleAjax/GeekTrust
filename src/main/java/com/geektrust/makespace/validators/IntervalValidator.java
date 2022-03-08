package com.geektrust.makespace.validators;

import com.geektrust.makespace.entities.Interval;
import com.geektrust.makespace.exceptions.IncorrectInputException;
import com.geektrust.makespace.util.TimeUtil;

public class IntervalValidator {
    public void validate(final String startTime, final String endTime) throws IncorrectInputException {

        if(!TimeUtil.isValidTimeFormat(startTime) || !TimeUtil.isValidTimeFormat(endTime)){
            throw new IncorrectInputException();
        }
        Interval interval = new Interval(startTime, endTime);
        if(interval==null ||
            interval.isEndBeforeStart() ||
            !interval.is15MinFormat()) {
            throw new IncorrectInputException();
        }
    }
}
