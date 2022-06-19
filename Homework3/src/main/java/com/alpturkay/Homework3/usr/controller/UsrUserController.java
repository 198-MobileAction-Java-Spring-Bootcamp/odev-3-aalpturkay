package com.alpturkay.Homework3.usr.controller;

import com.alpturkay.Homework3.gen.response.RestResponse;
import com.alpturkay.Homework3.usr.dao.UsrUserDao;
import com.alpturkay.Homework3.usr.dto.UsrUserDto;
import com.alpturkay.Homework3.usr.dto.UsrUserSaveRequestDto;
import com.alpturkay.Homework3.usr.dto.UsrUserUpdatePasswordRequestDto;
import com.alpturkay.Homework3.usr.service.UsrUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsrUserController {
    private final UsrUserService usrUserService;
    
    @PostMapping
    ResponseEntity<UsrUserDto> save(@RequestBody UsrUserSaveRequestDto usrUserSaveRequestDto){
        UsrUserDto usrUserDto = usrUserService.save(usrUserSaveRequestDto);
        return new ResponseEntity<>(usrUserDto, HttpStatus.CREATED);
    }

    @PatchMapping("{id}/updatePassword")
    ResponseEntity updatePassword(@RequestBody UsrUserUpdatePasswordRequestDto usrUserUpdatePasswordRequestDto,
                                  @PathVariable Long id){
        usrUserService.updatePassword(id, usrUserUpdatePasswordRequestDto);
        return ResponseEntity.ok(RestResponse.empty());
    }
}
