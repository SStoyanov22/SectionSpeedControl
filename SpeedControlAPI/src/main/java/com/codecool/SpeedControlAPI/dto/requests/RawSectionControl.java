package com.codecool.SpeedControlAPI.dto.requests;

import jakarta.validation.constraints.NotBlank;

public class RawSectionControl {
    @NotBlank(message = "Distance is a mandatory field.")
    private double distance;

}
