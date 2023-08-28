package com.newproduct.springboot.service;


import com.newproduct.springboot.entity.Product;
import com.newproduct.springboot.mapper.ProductMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public int save(Product product) {
        if (product.getId() == null){ //这里有bug 如果
            return productMapper.insert(product);
        }else{
            return productMapper.update(product);
        }
    }

}