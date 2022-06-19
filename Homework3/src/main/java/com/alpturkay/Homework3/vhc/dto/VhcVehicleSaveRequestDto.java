package com.alpturkay.Homework3.vhc.dto;

import lombok.Data;

@Data
public class VhcVehicleSaveRequestDto {
    private String make;

    private String brand;

    private String plate;

    private Long usrUserId;
}
