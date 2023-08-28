package com.newproduct.springboot.Repository;

import com.newproduct.springboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    // 可根据需要添加其他自定义的查询方法
}