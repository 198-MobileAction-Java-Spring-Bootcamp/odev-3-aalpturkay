package com.alpturkay.Homework3.jwt.security;

import com.alpturkay.Homework3.usr.entity.UsrUser;
import com.alpturkay.Homework3.usr.service.UsrUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private UsrUserService usrUserService;

    public void setUsrUserService(UsrUserService usrUserService){
        this.usrUserService = usrUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsrUser usrUser = usrUserService.findByUsername(username);
        return JwtUserDetails.create(usrUser);
    }

    public UserDetails loadByUserId(Long id){
        UsrUser usrUser = usrUserService.findByIdWithControl(id);
        System.out.println(usrUser.getUsername());
        return JwtUserDetails.create(usrUser);
    }
}
