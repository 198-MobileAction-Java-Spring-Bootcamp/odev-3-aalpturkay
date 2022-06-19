package com.alpturkay.Homework3.jwt.dto;

import lombok.Data;

@Data
public class JwtLoginRequestDto {
    private String username;
    private String password;
}
