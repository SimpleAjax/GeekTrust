package com.geektrust.makespace.validators;

import com.geektrust.makespace.entities.Interval;
import com.geektrust.makespace.exceptions.IncorrectInputException;
import com.geektrust.makespace.exceptions.OverlappingIntervalException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

    BufferOverlapValidator bufferOverlapValidator;
    CapacityValidator capacityValidator;
    IntervalValidator intervalValidator;

    @BeforeEach
    public void setup() {
        bufferOverlapValidator = new BufferOverlapValidator();
        capacityValidator = new CapacityValidator();
        intervalValidator = new IntervalValidator();
    }

    @Test
    public void BufferOverlapTest() {
        Interval interval1 = new Interval("09:00", "10:45");
        Interval interval2 = new Interval("13:00", "14:45");
        Interval interval3 = new Interval("18:00", "19:00");
        Interval interval4 = new Interval("09:15", "09:30");

        Assertions.assertThrows(OverlappingIntervalException.class,
                () -> bufferOverlapValidator.validate(interval1));
        Assertions.assertThrows(OverlappingIntervalException.class,
                () -> bufferOverlapValidator.validate(interval2));
        Assertions.assertThrows(OverlappingIntervalException.class,
                () -> bufferOverlapValidator.validate(interval3));

        Assertions.assertDoesNotThrow(()->bufferOverlapValidator.validate(interval4));
    }

    @Test
    public void IntervalValidatorTest() {
        invalidIntervalAssert("23:00", "01:00");
        invalidIntervalAssert("11:00", "10:00");
        invalidIntervalAssert("23:00", "24:00");
        validIntervalAssert("00:00", "00:15");
        invalidIntervalAssert("3409", "10:15");
        invalidIntervalAssert("12:14", "1:00");
        invalidIntervalAssert("11:60", "12:15");
    }

    private void invalidIntervalAssert(String startTime, String endTime) {
        Assertions.assertThrows(IncorrectInputException.class,
                () -> intervalValidator.validate(startTime, endTime));
    }

    private void validIntervalAssert(String startTime, String endTime) {
        Assertions.assertDoesNotThrow(() -> intervalValidator.validate(startTime, endTime));
    }
}
