package com.codecool.SpeedControlAPI.dto.responses;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SectionControlStatus {
    private int totalSectionVehicles;
    private double intensity;
}