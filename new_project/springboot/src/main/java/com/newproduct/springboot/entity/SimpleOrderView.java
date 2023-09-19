package com.newproduct.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class SimpleOrderView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String orderid;
    private String sku;
    private String warehousename;
    private String trackingid;
    private String date;
    private String status;
    private String flag;

}
