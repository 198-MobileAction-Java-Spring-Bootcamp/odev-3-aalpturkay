package com.alpturkay.Homework3.vhc.converter;

import com.alpturkay.Homework3.vhc.dto.VhcVehicleDto;
import com.alpturkay.Homework3.vhc.entity.VhcVehicle;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VhcVehicleMapper {
    VhcVehicleMapper INSTANCE = Mappers.getMapper(VhcVehicleMapper.class);
    VhcVehicleDto convertToVhcVehicleDto(VhcVehicle vhcVehicle);
}
