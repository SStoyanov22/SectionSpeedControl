package com.codecool.SpeedControlAPI.utils;

import com.codecool.SpeedControlAPI.dto.requests.RawSectionVehicle;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import org.apache.coyote.BadRequestException;

public class TimeValidator {
    public static boolean isValidTime(String timeStr) throws DateTimeParseException{
        try {
            LocalTime.parse(timeStr);
            return true;
        } catch (DateTimeParseException ex){
            return false;
        }
    }
}
