package com.alpturkay.Homework3.vhc.dto;

import lombok.Data;

@Data
public class VhcVehicleDto {
    private Long id;

    private String make;

    private String brand;

    private String plate;

    private Long usrUserId;
}
