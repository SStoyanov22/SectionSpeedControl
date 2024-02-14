package org.sectionspeedcontrol;

import java.time.LocalTime;
import java.util.List;

public interface SpeedController {
    int countNumberOfVehiclesBeforeTime(LocalTime time);
    int countNumberOfVehiclesAtTime(LocalTime atTime);
    double calculateTrafficIntensityAtTime(LocalTime atTime);
    double percentageOfSpeedingVehicles();
    Vehicle findSpeedster();
    int countOvertakenVehicles(Vehicle vehicle);
    List<Vehicle> getVehicles();
    VehicleDataProcessor getDataProcessor();


}
