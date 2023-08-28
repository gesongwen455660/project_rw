package com.newproduct.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "productlogs")
public class ProductLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer productid;
    private  String sku;
    private  String sku13;
    private  String color;
    private  String chicun1;
    private  String chicun2;
    private  String weight1;
    private  String weight2;
    private  String input;
    private  String quantityHQ;
    private  String type; //产品类型
    private  String timestamp;//更改时间

}
