package com.newproduct.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class WfrwOrderOutsea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ponumber;
    private String warehousename;
    private String sku;
    private String trackingid;
    private String readyforpickupdate;
}
