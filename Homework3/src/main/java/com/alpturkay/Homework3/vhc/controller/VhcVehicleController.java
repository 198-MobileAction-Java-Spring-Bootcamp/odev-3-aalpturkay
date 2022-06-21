package com.alpturkay.Homework3.vhc.controller;

import com.alpturkay.Homework3.gen.response.RestResponse;
import com.alpturkay.Homework3.vhc.dto.VhcVehicleDto;
import com.alpturkay.Homework3.vhc.dto.VhcVehicleSaveRequestDto;
import com.alpturkay.Homework3.vhc.service.VhcVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VhcVehicleController {

    private final VhcVehicleService vhcVehicleService;

    @PostMapping
    public ResponseEntity save(@RequestBody VhcVehicleSaveRequestDto vhcVehicleSaveRequestDto){
        VhcVehicleDto vhcVehicleDto = vhcVehicleService.save(vhcVehicleSaveRequestDto);
        return ResponseEntity.ok(RestResponse.of(vhcVehicleDto));
    }

    @GetMapping
    public ResponseEntity findAll(){
        List<VhcVehicleDto> vhcVehicleDtoList = vhcVehicleService.findAll();
        return ResponseEntity.ok(RestResponse.of(vhcVehicleDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        VhcVehicleDto vhcVehicleDto = vhcVehicleService.findByIdWithControl(id);
        return ResponseEntity.ok(RestResponse.of(vhcVehicleDto));
    }


}
