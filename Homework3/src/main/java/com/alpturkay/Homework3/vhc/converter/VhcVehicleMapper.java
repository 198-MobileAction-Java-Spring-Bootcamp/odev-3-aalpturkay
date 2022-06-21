package com.alpturkay.Homework3.vhc.converter;

import com.alpturkay.Homework3.vhc.dto.VhcVehicleDto;
import com.alpturkay.Homework3.vhc.entity.VhcVehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VhcVehicleMapper {
    VhcVehicleMapper INSTANCE = Mappers.getMapper(VhcVehicleMapper.class);

    @Mapping(target = "usrUserId", source = "usrUser.id")
    VhcVehicleDto convertToVhcVehicleDto(VhcVehicle vhcVehicle);

    List<VhcVehicleDto> convertToVhcVehicleDtoList(List<VhcVehicle> vhcVehicleList);
}
