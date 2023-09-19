package com.newproduct.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ProductOld {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private  String url1;
    private  String url2;
    private  String sku;
    private  String sku13;
    private  String color;
    private  String chicun1;
    private  String chicun2;
    private  String weight1;
    private  String weight2;
    private  String input;
    private  String quantityHQ;
    private  String type;
}
