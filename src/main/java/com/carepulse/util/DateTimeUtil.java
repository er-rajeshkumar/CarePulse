package com.carepulse.util;

import java.time.LocalTime;

public class DateTimeUtil {
    public static boolean isValidTimeRange(
            LocalTime startTime,
            LocalTime endTime) {

        return startTime != null
                && endTime != null
                && startTime.isBefore(endTime);
    }
}
