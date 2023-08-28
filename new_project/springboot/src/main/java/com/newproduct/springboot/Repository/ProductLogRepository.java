package com.newproduct.springboot.Repository;

import com.newproduct.springboot.entity.ProductLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductLogRepository extends JpaRepository<ProductLog, Integer> {
    List<ProductLog> findByProductid(Integer productid);
}