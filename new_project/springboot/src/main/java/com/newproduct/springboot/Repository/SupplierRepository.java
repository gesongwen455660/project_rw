package com.newproduct.springboot.Repository;


import com.newproduct.springboot.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    // 可根据需要添加其他自定义的查询方法
}