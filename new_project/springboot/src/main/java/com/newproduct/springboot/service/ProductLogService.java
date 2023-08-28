//package com.newproduct.springboot.service;
//
//import com.newproduct.springboot.Repository.ProductLogRepository;
//import com.newproduct.springboot.entity.ProductLog;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ProductLogService {
//    private final ProductLogRepository productLogRepository;
//
//    @Autowired
//    public ProductLogService(ProductLogRepository productLogRepository) {
//        this.productLogRepository = productLogRepository;
//    }
//
//    public List<ProductLog> getProductLogsByProductId(Integer productId) {
//        return productLogRepository.findByProductId(productId);
//    }
//}
