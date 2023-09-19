package com.newproduct.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OrderOutsea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String orderid;
    private String date;
    private String flag;//店铺id
    private String type;
    private String amazonorderid;
    private String sku;
    private String status;

}
