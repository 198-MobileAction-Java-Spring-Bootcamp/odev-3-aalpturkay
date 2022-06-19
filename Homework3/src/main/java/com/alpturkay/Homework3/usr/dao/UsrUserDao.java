package com.alpturkay.Homework3.usr.dao;

import com.alpturkay.Homework3.usr.entity.UsrUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrUserDao extends JpaRepository<UsrUser, Long> {
    boolean existsByUsername(String username);
    UsrUser findByUsername(String username);

}
