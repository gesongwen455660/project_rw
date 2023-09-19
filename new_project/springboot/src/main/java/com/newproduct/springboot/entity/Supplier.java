package com.newproduct.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "suppliername")
    private String supplierName;
    @Column(name = "supplierid")
    private String supplierId;
    @Column(name = "producttype")
    private String productType;
    @Column(name = "supplieraddress")
    private String supplierAddress;
    @Column(name = "relationman")
    private String relationMan;
    @Column(name = "zhiwu")
    private String zhiWu;
    private String phone;
    private String date;

}
