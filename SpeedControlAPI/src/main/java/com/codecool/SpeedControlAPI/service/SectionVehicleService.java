package com.codecool.SpeedControlAPI.service;

import com.codecool.SpeedControlAPI.dto.requests.RawSectionVehicle;
import com.codecool.SpeedControlAPI.dto.responses.SectionControlStatistics;
import com.codecool.SpeedControlAPI.dto.responses.SectionControlStatus;
import com.codecool.SpeedControlAPI.dto.responses.Speedster;
import com.codecool.SpeedControlAPI.model.SectionVehicle;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public interface SectionVehicleService {

    void addSectionVehicles(RawSectionVehicle rawSectionVehicle) throws DateTimeParseException;
    List<SectionVehicle> getAllSectionVehicles();
    int getSectionVehiclesCountBeforeTime(String timeStr) throws DateTimeParseException;
    int getSectionVehiclesCountAtTime(String timeStr) throws DateTimeParseException;
    int getSectionVehiclesIntensityAtTime(String timeStr) throws DateTimeParseException;
    SectionVehicle getHighestSpeedVehicle();
    int countOvertakenVehicles(SectionVehicle vehicle);
    double getPercentageOfSpeedingVehicles();
}
