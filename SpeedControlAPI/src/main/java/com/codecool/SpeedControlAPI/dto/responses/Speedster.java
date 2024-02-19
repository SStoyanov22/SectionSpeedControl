package com.codecool.SpeedControlAPI.dto.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Speedster {
    private String licensePlate;
    private double avgSpeed;
    private int overtaken;

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("The data of the vehicle with the highest speed are:");
        output.append(String.format(
                "license plate number: %s \n",
                this.licensePlate));
        output.append(String.format(
                "average speed: %f km/h \n",
                this.avgSpeed));
        output.append(String.format(
                "number of overtaken vehicles: %d \n\n",
                this.overtaken));
        return super.toString();
    }
}
