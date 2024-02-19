package com.codecool.SpeedControlAPI.dto.responses;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SectionControlStatistics {
    private Speedster speedster;
    private float percentSpeedingVehicles;
    private int totalSectionVehicles;
    private int totalSectionVehiclesUntilNine;
}
