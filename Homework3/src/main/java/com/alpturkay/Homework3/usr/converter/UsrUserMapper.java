package com.alpturkay.Homework3.usr.converter;

import com.alpturkay.Homework3.usr.dto.UsrUserDto;
import com.alpturkay.Homework3.usr.dto.UsrUserSaveRequestDto;
import com.alpturkay.Homework3.usr.dto.UsrUserUpdatePasswordRequestDto;
import com.alpturkay.Homework3.usr.entity.UsrUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsrUserMapper {
    UsrUserMapper INSTANCE = Mappers.getMapper(UsrUserMapper.class);

    UsrUser convertToUsrUser(UsrUserSaveRequestDto usrUserSaveRequestDto);
    UsrUserDto convertToUsrUserDto(UsrUser usrUser);

}
