package com.newproduct.springboot.Repository;

import com.newproduct.springboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<Product, Long> {
    // 可以添加其他自定义的数据库操作方法，根据需要进行定义
}