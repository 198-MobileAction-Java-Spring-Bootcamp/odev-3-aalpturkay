package com.alpturkay.Homework3.usr.dto;

import lombok.Data;

@Data
public class UsrUserUpdatePasswordRequestDto {

    private String previousPassword;
    private String newPassword;
    private String newPasswordAgain;

}
