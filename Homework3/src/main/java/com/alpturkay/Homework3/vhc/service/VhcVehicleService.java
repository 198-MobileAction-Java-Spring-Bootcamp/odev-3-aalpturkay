package com.alpturkay.Homework3.vhc.service;

import com.alpturkay.Homework3.gen.enums.GenErrorMessage;
import com.alpturkay.Homework3.gen.exceptions.GenBusinessException;
import com.alpturkay.Homework3.gen.exceptions.ItemConflictException;
import com.alpturkay.Homework3.gen.exceptions.ItemNotFoundException;
import com.alpturkay.Homework3.gen.exceptions.TurkishLetterException;
import com.alpturkay.Homework3.usr.converter.UsrUserMapper;
import com.alpturkay.Homework3.usr.dto.UsrUserDto;
import com.alpturkay.Homework3.usr.entity.UsrUser;
import com.alpturkay.Homework3.usr.service.UsrUserService;
import com.alpturkay.Homework3.vhc.converter.VhcVehicleMapper;
import com.alpturkay.Homework3.vhc.dao.VhcVehicleDao;
import com.alpturkay.Homework3.vhc.dto.VhcVehicleDto;
import com.alpturkay.Homework3.vhc.dto.VhcVehicleSaveRequestDto;
import com.alpturkay.Homework3.vhc.entity.VhcVehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VhcVehicleService {

    private final VhcVehicleDao vhcVehicleDao;
    private final UsrUserService usrUserService;

    public VhcVehicleDto save(VhcVehicleSaveRequestDto vhcVehicleSaveRequestDto){

        controlPlateExists(vhcVehicleSaveRequestDto);

        vhcVehicleSaveRequestDto.setPlate(vhcVehicleSaveRequestDto.getPlate().toUpperCase(Locale.ROOT)
                .replace("\\s", ""));

        controlTurkishLetter(vhcVehicleSaveRequestDto);

        UsrUser usrUser = usrUserService.findByIdWithControl(vhcVehicleSaveRequestDto.getUsrUserId());

        VhcVehicle vhcVehicle = convertToVhcVehicle(vhcVehicleSaveRequestDto, usrUser);
        vhcVehicle = vhcVehicleDao.save(vhcVehicle);
        VhcVehicleDto vhcVehicleDto = VhcVehicleMapper.INSTANCE.convertToVhcVehicleDto(vhcVehicle);
        vhcVehicleDto.setUsrUserId(vhcVehicleSaveRequestDto.getUsrUserId());

        return vhcVehicleDto;
    }

    private void controlTurkishLetter(VhcVehicleSaveRequestDto vhcVehicleSaveRequestDto) {
        if (containsTurkishLetter(vhcVehicleSaveRequestDto.getPlate()))
            throw new TurkishLetterException(GenErrorMessage.PLATE_CANNOT_CONTAIN_TURKISH_LETTERS);
    }

    private void controlPlateExists(VhcVehicleSaveRequestDto vhcVehicleSaveRequestDto) {
        if (existsByPlate(vhcVehicleSaveRequestDto.getPlate()))
            throw new ItemConflictException(GenErrorMessage.ITEM_CONFLICT);
    }

    public List<VhcVehicleDto> findAll(){
        List<VhcVehicle> vhcVehicles = vhcVehicleDao.findAll();

        List<VhcVehicleDto> vhcVehicleDtoList = VhcVehicleMapper.INSTANCE.convertToVhcVehicleDtoList(vhcVehicles);

        return vhcVehicleDtoList;
    }

    public List<VhcVehicle> findByMakeAndBrand(String make, String brand){
        List<VhcVehicle> vhcVehicles = vhcVehicleDao.findByMakeAndBrand(make, brand);
        return vhcVehicles;
    }

    public boolean existsByPlate(String plate){
        boolean exists = vhcVehicleDao.existsByPlate(plate);
        return exists;
    }

    private VhcVehicle convertToVhcVehicle(VhcVehicleSaveRequestDto vhcVehicleSaveRequestDto, UsrUser usrUser) {
        VhcVehicle vhcVehicle = new VhcVehicle();
        vhcVehicle.setUsrUser(usrUser);
        vhcVehicle.setBrand(vhcVehicleSaveRequestDto.getBrand());
        vhcVehicle.setMake(vhcVehicleSaveRequestDto.getMake());
        vhcVehicle.setPlate(vhcVehicleSaveRequestDto.getPlate());
        return vhcVehicle;
    }

    private boolean containsTurkishLetter(String text){
        String turkishLetters = "ğĞşŞİçÇıüÜöÖ";
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < turkishLetters.length(); j++) {
                if (Character.isLetter(text.charAt(i))){
                    if (text.charAt(i) == turkishLetters.charAt(j))
                        return true;
                }
            }
        }
        return false;
    }

    public VhcVehicleDto findByIdWithControl(Long id) {
        Optional<VhcVehicle> optionalVhcVehicle = vhcVehicleDao.findById(id);
        VhcVehicle vhcVehicle;
        VhcVehicleDto vhcVehicleDto;
        if (optionalVhcVehicle.isPresent()){
            vhcVehicle = optionalVhcVehicle.get();
            vhcVehicleDto = VhcVehicleMapper.INSTANCE.convertToVhcVehicleDto(vhcVehicle);
        } else {
            throw new ItemNotFoundException(GenErrorMessage.ITEM_NOT_FOUND);
        }
        return vhcVehicleDto;
    }
}
