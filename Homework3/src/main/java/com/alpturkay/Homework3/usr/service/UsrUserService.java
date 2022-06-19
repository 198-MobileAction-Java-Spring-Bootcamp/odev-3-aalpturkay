package com.alpturkay.Homework3.usr.service;

import com.alpturkay.Homework3.gen.enums.GenErrorMessage;
import com.alpturkay.Homework3.gen.exceptions.ItemConflictException;
import com.alpturkay.Homework3.gen.exceptions.ItemNotFoundException;
import com.alpturkay.Homework3.gen.exceptions.PasswordsDontMatchException;
import com.alpturkay.Homework3.usr.converter.UsrUserMapper;
import com.alpturkay.Homework3.usr.dao.UsrUserDao;
import com.alpturkay.Homework3.usr.dto.UsrUserDto;
import com.alpturkay.Homework3.usr.dto.UsrUserSaveRequestDto;
import com.alpturkay.Homework3.usr.dto.UsrUserUpdatePasswordRequestDto;
import com.alpturkay.Homework3.usr.entity.UsrUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsrUserService {

    private final UsrUserDao usrUserDao;
    private final PasswordEncoder passwordEncoder;

    public UsrUserDto save(UsrUserSaveRequestDto usrUserSaveRequestDto){
        UsrUser usrUser = UsrUserMapper.INSTANCE.convertToUsrUser(usrUserSaveRequestDto);
        String password = passwordEncoder.encode(usrUser.getPassword());

        if (existsByUsername(usrUser.getUsername()))
            throw new ItemConflictException(GenErrorMessage.USER_ALREADY_EXISTS);

        usrUser.setPassword(password);
        usrUser = usrUserDao.save(usrUser);
        UsrUserDto usrUserDto = UsrUserMapper.INSTANCE.convertToUsrUserDto(usrUser);
        return usrUserDto;
    }

    public void updatePassword(Long userId, UsrUserUpdatePasswordRequestDto usrUserUpdatePasswordRequestDto){
        
        String prePassword = usrUserUpdatePasswordRequestDto.getPreviousPassword();
        String newPassword = usrUserUpdatePasswordRequestDto.getNewPassword();
        String newPasswordAgain = usrUserUpdatePasswordRequestDto.getNewPasswordAgain();
        
        Optional<UsrUser> userOptional = usrUserDao.findById(userId);
        if (userOptional.isPresent()){
            UsrUser usrUser = userOptional.get();
            String usrUserPasswordHash = usrUser.getPassword();
            checkPreviousPasswordAndHash(prePassword, usrUserPasswordHash);
            checkPasswords(newPassword, newPasswordAgain);
            String newPasswordHash = passwordEncoder.encode(newPassword);
            usrUser.setPassword(newPasswordHash);
            usrUserDao.save(usrUser);
        }
    }

    public UsrUser findByUsername(String username){
        return usrUserDao.findByUsername(username);
    }

    public UsrUser findByIdWithControl(Long id){
        Optional<UsrUser> optionalUsrUser = usrUserDao.findById(id);

        UsrUser usrUser;

        if (optionalUsrUser.isPresent()) {
            usrUser = optionalUsrUser.get();
        } else {
            throw new ItemNotFoundException(GenErrorMessage.ITEM_NOT_FOUND);
        }
        return usrUser;
    }

    public boolean existsByUsername(String username){
        boolean exists = usrUserDao.existsByUsername(username);
        return exists;
    }

    private void checkPasswords(String firstPassword, String secondPassword) {
        if (!firstPassword.equals(secondPassword)){
            throw new PasswordsDontMatchException(GenErrorMessage.PASSWORDS_DONT_MATCH);
        }
    }

    private void checkPreviousPasswordAndHash(String prePassword, String usrUserPasswordHash) {
        if (!passwordEncoder.matches(prePassword, usrUserPasswordHash))
            throw new PasswordsDontMatchException(GenErrorMessage.PASSWORDS_DONT_MATCH);
    }

}
