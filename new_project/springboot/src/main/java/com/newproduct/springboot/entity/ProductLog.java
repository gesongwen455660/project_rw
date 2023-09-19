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
    private String factory; //工厂
    private String sku;
    private String fba;
    private String fbm;
    private String fulfillmentfeeamazon;
    private String fulfillmentfeeoutsea;
    private String productlenghcm1;
    private String productlenghcm2;
    private String productshortlenghcm;
    private String outboxlenghcm1;
    private String outboxlenghcm2;
    private String outboxshortlenghcm;
    private String weightkg1;
    private String weightkg2;
    private String productlenghinch1;
    private String productlenghinch2;
    private String productshortlenghinch;
    private String outboxlenghinch1;
    private String outboxlenghinch2;
    private String outboxshortlenghinch;
    private String weightlb1;
    private String weightlb2;
    private String quantityhq;
    private String quantity;
    private String inboxlenghinch1;
    private String inboxlenghinch2;
    private String inboxshortlenghinch;
    private String inboxweightlb1;
    private String inboxweightlb2;
    private String inboxlenghcm1;
    private String inboxlenghcm2;
    private String inboxshortlenghcm;
    private String inboxweightkg1;
    private String inboxweightkg2;
    private String minlenghinchheight;
    private String minlenghinchwidth;
    private String minlenghinchhigh;
    private String minweight1;
    private String minweight2;
    private String type; //产品类型
    private String timestamp;//更改时间

}
