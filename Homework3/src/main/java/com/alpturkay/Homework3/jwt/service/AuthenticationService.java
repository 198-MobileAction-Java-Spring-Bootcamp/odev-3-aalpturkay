package com.alpturkay.Homework3.jwt.service;

import com.alpturkay.Homework3.jwt.dto.JwtLoginRequestDto;
import com.alpturkay.Homework3.jwt.enums.JwtConstant;
import com.alpturkay.Homework3.jwt.security.JwtTokenGenerator;
import com.alpturkay.Homework3.usr.dto.UsrUserDto;
import com.alpturkay.Homework3.usr.dto.UsrUserSaveRequestDto;
import com.alpturkay.Homework3.usr.service.UsrUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UsrUserService usrUserService;
    private final JwtTokenGenerator jwtTokenGenerator;

    public UsrUserDto register(UsrUserSaveRequestDto usrUserSaveRequestDto) {
        return usrUserService.save(usrUserSaveRequestDto);
    }

    public String login(JwtLoginRequestDto jwtLoginRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                jwtLoginRequestDto.getUsername(), jwtLoginRequestDto.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJwtToken(authentication);

        String fullToken = JwtConstant.BEARER.getConstant() + token;

        return fullToken;
    }
}
