package com.codecool.SpeedControlAPI.controller;

import com.codecool.SpeedControlAPI.dto.requests.RawSectionVehicle;
import com.codecool.SpeedControlAPI.dto.responses.SectionControlStatus;
import com.codecool.SpeedControlAPI.dto.responses.SectionControlStatistics;
import com.codecool.SpeedControlAPI.dto.responses.Speedster;
import com.codecool.SpeedControlAPI.model.SectionVehicle;
import com.codecool.SpeedControlAPI.service.SectionVehicleService;
import jakarta.validation.Valid;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SectionVehicleController {
    SectionVehicleService sectionVehicleService;

    @PostMapping(value = "/sectionvehicle")
    public ResponseEntity<HttpStatus> addSectionControlVehicles(@Valid @RequestBody, RawSectionVehicle rawSectionVehicle) {

        sectionVehicleService.addSectionVehicles(rawSectionVehicle);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value="/sectionvehicles/count")
    public ResponseEntity<String> getSectionVehiclesCount(){
        String response = String.format(
                "The data of %d vehicles were recorded in the measurement. \n\n",
                sectionVehicleService.getAllSectionVehicles().size())
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(value = "/sectionvehicles/before/{time}")
    public ResponseEntity<List<SectionVehicle>> getSectionVehiclesCountBeforeTime(@PathVariable("time") String timeStr) {
        int count = sectionVehicleService.getSectionVehiclesCountBeforeTime(timeStr);
        LocalTime time = LocalTime.parse(timeStr);
        String response = String.format(
                "Before %s o'clock %d vehicles passed the exit point recorder. \n\n",
                time,
                count
                );

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(value = "/sectionvehicles/before/{time}")
    public ResponseEntity<List<SectionVehicle>> getz(@PathVariable("time") String timeStr) {
        SectionControlStatus status = SectionControlStatus.builder()
                .totalSectionVehicles(sectionVehicleService.getSectionVehiclesCountAtTime(timeStr))
                .intensity(sectionVehicleService.getSectionVehiclesIntensityAtTime(timeStr))
                .build();
        StringBuilder response = new StringBuilder();
        LocalTime atTime = LocalTime.parse(timeStr);
        response.append(String.format(
                "a. The number of vehicles that passed the entry point recorder: %d \n",
                status.getTotalSectionVehicles()));
        response.append(String.format(
                "b. The traffic intensity: %f \n\n",
                status.getIntensity()));

        return new ResponseEntity(response.toString(), HttpStatus.OK);
    }



    @GetMapping(value = "/sectionvehicles/speedster")
    public ResponseEntity<String> getSpeedster() {
        SectionVehicle vehicle = sectionVehicleService.getHighestSpeedVehicle();
        Speedster speedster = Speedster.builder()
                .avgSpeed(vehicle.getAverageSpeed())
                .licensePlate(vehicle.getLicensePlate())
                .overtaken(sectionVehicleService.countOvertakenVehicles(vehicle))
                .build();
        return new ResponseEntity(speedster.toString(),HttpStatus.OK);
    }

    @GetMapping(value = "/sectionvehicles/speedster")
    public ResponseEntity<SectionControlStatistics> getSpeedster() {
        SectionVehicle vehicle = sectionVehicleService.getHighestSpeedVehicle();
        Speedster speedster = Speedster.builder()
                .avgSpeed(vehicle.getAverageSpeed())
                .licensePlate(vehicle.getLicensePlate())
                .overtaken(sectionVehicleService.countOvertakenVehicles(vehicle))
                .build();
        return new ResponseEntity(speedster.toString(),HttpStatus.OK);
    }

}
