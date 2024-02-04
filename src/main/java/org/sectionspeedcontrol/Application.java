package org.sectionspeedcontrol;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        SpeedController speedController = new SectionSpeedController(10.0, "measurements.txt");

        //Exercise 2
        System.out.println("Exercise 2.");
        String ex2_output = String.format("The data of %d vehicles were recorded in the measurement. \n\n",speedController.getVehicles().size());
        System.out.printf(ex2_output);
        sb.append("Exercise 2.\n");
        sb.append(ex2_output);

        //Exercise 3
        System.out.println("Exercise 3.");
        LocalTime beforeTime = LocalTime.of(9,00);
        String ex3_output = String.format("Before %s o'clock %d vehicles passed the exit point recorder. \n\n",
                beforeTime,
                speedController.countNumberOfVehiclesBeforeTime(beforeTime));
        System.out.printf(ex3_output);
        sb.append("Exercise 3.\n");
        sb.append(ex3_output);

        //Exercise 4
        System.out.println("Exercise 4.");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an hour and minute value: ");
        String input = scanner.nextLine();
        String[] data = input.split(" ");
        int hour = Integer.parseInt(data[0]);
        int minute = Integer.parseInt(data[1]);
        LocalTime atTime = LocalTime.of(hour, minute);
        int vehiclesAtTime = speedController.countNumberOfVehiclesAtTime(atTime);
        double trafficIntensityAtTime = speedController.calculateTrafficIntensityAtTime(atTime);
        String ex4a_output = String.format("a. The number of vehicles that passed the entry point recorder: %d \n", vehiclesAtTime);
        String ex4b_output = String.format("b. The traffic intensity: %f \n\n", trafficIntensityAtTime);
        System.out.print(ex4a_output);
        System.out.print(ex4b_output);
        sb.append("Exercise 4.\n");
        sb.append(ex4a_output);
        sb.append(ex4b_output);

        //Exercise 5
        System.out.println("Exercise 5.");
        Vehicle speedster = speedController.findSpeedster();
        System.out.println("The data of the vehicle with the highest speed are:");
        String ex5a_output = String.format("license plate number: %s \n", speedster.getLicensePlate());
        String ex5b_output = String.format("average speed: %f km/h \n", speedster.getAverageSpeed());
        String ex5c_output = String.format("number of overtaken vehicles: %d \n\n", speedController.countOvertakenVehicles(speedster));
        System.out.print(ex5a_output);
        System.out.print(ex5b_output);
        System.out.print(ex5c_output);
        sb.append("Exercise 5.\n");
        sb.append("The data of the vehicle with the highest speed are:\n");
        sb.append(ex5a_output);
        sb.append(ex5b_output);
        sb.append(ex5c_output);

        //Exercise 6
        System.out.println("Exercise 6.");
        String ex6_output = String.format("%.2f%% percent of the vehicles were speeding.\n\n", speedController.percentageOfSpeedingVehicles());
        System.out.print(ex6_output);
        sb.append("Exercise 6.\n");
        sb.append(ex6_output);

        try {
            speedController.getDataProcessor().write(sb.toString(),"output.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}