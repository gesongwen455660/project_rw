package com.newproduct.springboot.service;

import com.newproduct.springboot.DTO.SupplierSaveDataDTO;
import com.newproduct.springboot.Repository.SupplierRepository;
import com.newproduct.springboot.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService    {
    @Autowired
    private SupplierRepository supplierRepository;

    public void saveSupplierData(SupplierSaveDataDTO supplierSaveDataDTO) {
        // 创建Supplier实体对象并设置属性值
        Supplier supplier = new Supplier();
        supplier.setSupplierName(supplierSaveDataDTO.getSupplierNameSubmit());
        supplier.setSupplierId(supplierSaveDataDTO.getSupplierIdSubmit());
        supplier.setProductType(supplierSaveDataDTO.getProductTypeSubmit());
        supplier.setSupplierAddress(supplierSaveDataDTO.getSupplierAddressSubmit());
        supplier.setRelationMan(supplierSaveDataDTO.getRelationManSubmit());
        supplier.setZhiWu(supplierSaveDataDTO.getZhiWuSubmit());
        supplier.setPhone(supplierSaveDataDTO.getPhoneSubmit());
        supplier.setDate(supplierSaveDataDTO.getDateSubmit());

        // 保存供应商数据
        supplierRepository.save(supplier);
    }
}
