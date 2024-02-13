package org.sectionspeedcontrol;

import java.time.LocalTime;
import java.util.Scanner;

public class JavaBasicsProblemSolver implements  ProblemSolver{

    public void run() {
        Scanner scan = new Scanner(System.in);
        System.out.print(
                "Enter the solution of the exercise you want to see: ");
        while(scan.hasNextLine()){
            Integer exerciseNumber = Integer.parseInt(scan.nextLine());
            SpeedController speedController = new SectionSpeedController(10, "measurements.txt");
            StringBuilder output = new StringBuilder();
            output.append(String.format("Exercise %s. \n",exerciseNumber));
            switch (exerciseNumber){
                case 2:
                    output.append(String.format(
                            "The data of %d vehicles were recorded in the measurement. \n\n",
                            speedController.getVehicles().size()));
                    break;
                case 3:
                    LocalTime beforeTime = LocalTime.of(9,00);
                    output.append(String.format(
                            "Before %s o'clock %d vehicles passed the exit point recorder. \n\n",
                            beforeTime,
                            speedController.countNumberOfVehiclesBeforeTime(beforeTime)));
                    break;
                case 4:
                    System.out.print("Enter an hour and minute value: ");
                    String input = scan.nextLine();
                    String[] data = input.split(" ");
                    LocalTime atTime = LocalTime.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
                    output.append(String.format(
                            "a. The number of vehicles that passed the entry point recorder: %d \n",
                            speedController.countNumberOfVehiclesAtTime(atTime)));
                    output.append(String.format(
                            "b. The traffic intensity: %f \n\n",
                            speedController.calculateTrafficIntensityAtTime(atTime)));

                    break;
                case 5:
                    Vehicle speedster = speedController.findSpeedster();
                    output.append("The data of the vehicle with the highest speed are:");
                    output.append(String.format(
                            "license plate number: %s \n",
                            speedster.getLicensePlate()));
                    output.append(String.format(
                            "average speed: %f km/h \n",
                            speedster.getAverageSpeed()));
                    output.append(String.format(
                            "number of overtaken vehicles: %d \n\n",
                            speedController.countOvertakenVehicles(speedster)));
                    break;
                case 6:
                    output.append(String.format(
                            "%.2f%% percent of the vehicles were speeding.\n\n",
                            speedController.percentageOfSpeedingVehicles()));
                default:
                    output.append("There's no output for an exercise with that number!\n\n");
                    break;
            }
            System.out.println(output);
        }
        scan.close();
    }
}
