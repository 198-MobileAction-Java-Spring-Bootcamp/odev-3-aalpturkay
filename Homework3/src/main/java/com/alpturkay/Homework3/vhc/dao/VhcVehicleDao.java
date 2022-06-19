package com.alpturkay.Homework3.vhc.dao;

import com.alpturkay.Homework3.vhc.entity.VhcVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VhcVehicleDao extends JpaRepository<VhcVehicle, Long> {
    List<VhcVehicle> findByMakeAndBrand(String make, String brand);
    boolean existsByPlate(String plate);
}
