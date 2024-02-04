package org.sectionspeedcontrol;

import java.time.LocalTime;
import lombok.Getter;

@Getter
public class Vehicle {
    public String licensePlate;
    private LocalTime entryTime;
    private LocalTime exitTime;
    private double averageSpeed;

    public Vehicle(String licensePlate, LocalTime entryTime, LocalTime exitTime) {
        this.licensePlate = licensePlate;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
}
