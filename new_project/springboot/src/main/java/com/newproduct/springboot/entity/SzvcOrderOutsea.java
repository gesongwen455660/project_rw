package com.newproduct.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "szvcorderoutsea")
public class SzvcOrderOutsea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String wcs1;
    private String orderid;
    private String trackingid;
    private String shippeddate;
    private String sku;


}
