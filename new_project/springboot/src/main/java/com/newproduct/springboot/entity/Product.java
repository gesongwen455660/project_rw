package com.newproduct.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private  String url;
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
}