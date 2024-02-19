package com.codecool.SpeedControlAPI.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public
class RawSectionVehicle {
    @NotBlank(message = "Plate number is mandatory")
    private String plateNumber;
    @NotBlank(message = "Time of entry is mandatory")
    private String entryTime;
    @NotBlank(message = "Time of exit is mandatory")
    private String exitTime;


}
