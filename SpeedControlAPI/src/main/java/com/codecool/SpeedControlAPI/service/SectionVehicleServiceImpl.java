package com.codecool.SpeedControlAPI.service;

import com.codecool.SpeedControlAPI.dto.requests.RawSectionVehicle;
import com.codecool.SpeedControlAPI.model.SectionVehicle;
import com.codecool.SpeedControlAPI.repository.SectionVehicleRepository;
import com.codecool.SpeedControlAPI.utils.Calculator;
import com.codecool.SpeedControlAPI.utils.RestConstants;
import com.codecool.SpeedControlAPI.utils.TimeValidator;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SectionVehicleServiceImpl implements SectionVehicleService {
    SectionVehicleRepository sectionVehicleRepository;
    @Override
    public void addSectionVehicles(RawSectionVehicle rawSectionVehicle) throws DateTimeParseException {
        TimeValidator.isValidTime(rawSectionVehicle.getEntryTime());
        TimeValidator.isValidTime(rawSectionVehicle.getExitTime());
        LocalTime entryTime = LocalTime
                .parse(rawSectionVehicle.getEntryTime());
        LocalTime exitTime = LocalTime
                .parse(rawSectionVehicle.getExitTime());
        SectionVehicle vehicle = SectionVehicle.builder()
                .licensePlate(rawSectionVehicle.getPlateNumber())
                .entryTime(entryTime)
                .exitTime(exitTime)
                .build();
        double averageSpeed = Calculator.calculateAverageSpeed(RestConstants.SECTION_DISTANCE, vehicle);
        vehicle.setAverageSpeed(averageSpeed);
        sectionVehicleRepository.save(vehicle);
    }

    @Override
    public List<SectionVehicle> getAllSectionVehicles() {
        return sectionVehicleRepository.findAll();
    }

    @Override
    public int getSectionVehiclesCountBeforeTime(String timeStr) throws DateTimeParseException{
        TimeValidator.isValidTime(timeStr);
        LocalTime time = LocalTime.parse(timeStr);

        return Calculator.calculateNumberVehiclesBeforeTime(time, sectionVehicleRepository.findAll());

    }

    @Override
    public int getSectionVehiclesCountAtTime(String timeStr) throws DateTimeParseException {
        TimeValidator.isValidTime(timeStr);
        LocalTime time = LocalTime.parse(timeStr);

        return Calculator.calculateNumberVehiclesAtTime(time, sectionVehicleRepository.findAll());
    }

    @Override
    public int getSectionVehiclesIntensityAtTime(String timeStr) throws DateTimeParseException {
        TimeValidator.isValidTime(timeStr);
        LocalTime time = LocalTime.parse(timeStr);

        return Calculator.calculateNumberVehiclesAtTime(time, sectionVehicleRepository.findAll());
    }

    @Override
    public SectionVehicle getHighestSpeedVehicle() {
        return Calculator.findHighestSpeedVehicle(sectionVehicleRepository.findAll());
    }

    @Override
    public int countOvertakenVehicles(SectionVehicle vehicle) {
        return Calculator.countOvertakenVehicles(vehicle, sectionVehicleRepository.findAll());
    }

    @Override
    public double getPercentageOfSpeedingVehicles() {
        return Calculator.calculatePercentageOfSpeedingVehicles(sectionVehicleRepository.findAll());
    }

}
