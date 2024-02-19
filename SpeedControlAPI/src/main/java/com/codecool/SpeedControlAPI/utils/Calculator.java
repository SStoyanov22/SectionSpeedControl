package com.codecool.SpeedControlAPI.utils;

import com.codecool.SpeedControlAPI.model.SectionVehicle;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import org.springframework.cglib.core.Local;

public class Calculator {
    public static double calculateAverageSpeed(double sectionDistance, SectionVehicle vehicle){
        return RestConstants.SECTION_DISTANCE /((double) Duration
                .between(vehicle.getEntryTime(),vehicle.getExitTime())
                .getSeconds()/3600);
    }


    public static int calculateNumberVehiclesBeforeTime(LocalTime time, List<SectionVehicle> vehicles){
        int count = 0;
        for (SectionVehicle vehicle: vehicles) {
            if (vehicle.getExitTime().isBefore(time)){
                count++;
            }
        }
        return count;
    }

    public static int calculateNumberVehiclesAtTime(LocalTime time, List<SectionVehicle> vehicles){
        int count = 0;
        for (SectionVehicle vehicle: vehicles){
            if(vehicle.getEntryTime().getHour() == time.getHour() &&
                    vehicle.getEntryTime().getMinute() == time.getMinute()){
                count++;
            }
        }
        return count;
    }


    public static double calculateVehiclesIntensityAt(LocalTime time, List<SectionVehicle> vehicles) {
        int vehiclesAtTime = 0;
        for (SectionVehicle vehicle: vehicles){
            if(vehicle.getExitTime().compareTo(time)>=0 &&
                    vehicle.getEntryTime().compareTo(time.plusMinutes(1))<0){
                vehiclesAtTime++;
            }
        }
        double trafficIntensity = (double) vehiclesAtTime / 10.0;
        return trafficIntensity;
    }


    public static double calculatePercentageOfSpeedingVehicles(List<SectionVehicle> vehicles) {
        int countSpeedingVehicles = 0;
        for (SectionVehicle vehicle : vehicles) {
            if (vehicle.getAverageSpeed() > 90.0) {
                countSpeedingVehicles++;
            }
        }
        double perc = (double)countSpeedingVehicles * 100/ vehicles.size();
        return perc;
    }

    public static SectionVehicle findHighestSpeedVehicle(List<SectionVehicle> vehicles) {
        SectionVehicle speedster = vehicles.get(0);
        for (SectionVehicle vehicle: vehicles) {
            if (vehicle.getAverageSpeed()>speedster.getAverageSpeed()){
                speedster = vehicle;
            }
        }
        return speedster;
    }

    public static int countOvertakenVehicles(SectionVehicle selectedVehicle, List<SectionVehicle> vehicles) {
        int countOvertakenVehicles = 0;
        for (SectionVehicle vehicle: vehicles) {
            if (selectedVehicle.getEntryTime().compareTo(vehicle.getEntryTime())>=0 &&
                    selectedVehicle.getExitTime().compareTo(vehicle.getExitTime())<0){
                countOvertakenVehicles++;
            }
        }
        return countOvertakenVehicles;
    }


}
