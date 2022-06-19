package com.alpturkay.Homework3.vhc.entity;

import com.alpturkay.Homework3.usr.entity.UsrUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "VHC_VEHICLE")
@Getter
@Setter
public class VhcVehicle {
    @Id
    @SequenceGenerator(name = "VhcVehicle", sequenceName = "VHC_VEHICLE_ID_SEQ")
    @GeneratedValue(generator = "VhcVehicle")
    private Long id;

    @Column(name = "MAKE")
    private String make;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "PLATE")
    private String plate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID_USR_USER")
    private UsrUser usrUser;
}
