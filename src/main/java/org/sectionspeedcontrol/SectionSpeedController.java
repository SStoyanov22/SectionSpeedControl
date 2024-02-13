package org.sectionspeedcontrol;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class SectionSpeedController implements SpeedController{
    private double distance;
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public VehicleDataProcessor getDataProcessor() {
        return dataProcessor;
    }

    private VehicleDataProcessor dataProcessor;
    private List<Vehicle> vehicles;
    public SectionSpeedController(double distance, String data){
        this.distance = distance;
        this.dataProcessor = new VehicleDataProcessor();
        this.vehicles = dataProcessor.read(data);
        //calculate average speed per vehicle
        updateVehiclesAverageSpeed();
    }

    private void updateVehiclesAverageSpeed(){
        for (Vehicle vehicle:vehicles) {
            double averageSpeed = this.calculateVehicleAverageSpeed(vehicle);
            vehicle.setAverageSpeed(averageSpeed);
        }
    }

    private double calculateVehicleAverageSpeed(Vehicle vehicle) {
        Duration duration = Duration.between(vehicle.getEntryTime(), vehicle.getExitTime());
        double durationInSeconds = (double) duration.getSeconds();
        return this.distance/(durationInSeconds/3600);
    }

    @Override
    public int countNumberOfVehiclesBeforeTime(LocalTime time) {
        int count = 0;
        for (Vehicle vehicle: vehicles) {
            if (vehicle.getExitTime().isBefore(time)){
                count++;
            }
        }
        return count;
    }

    @Override
    public int countNumberOfVehiclesAtTime(LocalTime atTime) {
        int count = 0;
        for (Vehicle vehicle: vehicles){
            if(vehicle.getEntryTime().getHour() == atTime.getHour() &&
                    vehicle.getEntryTime().getMinute() == atTime.getMinute()){
                count++;
            }
        }
        return count;
    }

    @Override
    public double calculateTrafficIntensityAtTime(LocalTime atTime) {
        int vehiclesAtTime = 0;
        for (Vehicle vehicle: vehicles){
            if(vehicle.getExitTime().compareTo(atTime)>=0 &&
                    vehicle.getEntryTime().compareTo(atTime.plusMinutes(1))<0){
                vehiclesAtTime++;
            }
        }
        double trafficIntensity = (double) vehiclesAtTime / 10.0;
        return trafficIntensity;
    }

    @Override
    public double percentageOfSpeedingVehicles() {
        int countSpeedingVehicles = 0;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getAverageSpeed() > 90.0) {
                countSpeedingVehicles++;
            }
        }
        double perc = (double)countSpeedingVehicles * 100/ vehicles.size();
        return perc;
    }

    @Override
    public Vehicle findSpeedster() {
        Vehicle speedster = vehicles.get(0);
        for (Vehicle vehicle: this.vehicles) {
            if (vehicle.getAverageSpeed()>speedster.getAverageSpeed()){
                speedster = vehicle;
            }
        }
        return speedster;
    }

    @Override
    public int countOvertakenVehicles(Vehicle selectedVehicle) {
        int countOvertakenVehicles = 0;
        for (Vehicle vehicle: this.vehicles) {
            if (selectedVehicle.getEntryTime().compareTo(vehicle.getEntryTime())>=0 &&
                    selectedVehicle.getExitTime().compareTo(vehicle.getExitTime())<0){
                countOvertakenVehicles++;
            }
        }
        return countOvertakenVehicles;
    }
}
