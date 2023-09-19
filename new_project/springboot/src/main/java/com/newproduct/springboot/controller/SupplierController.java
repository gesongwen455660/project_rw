package com.newproduct.springboot.controller;

import com.newproduct.springboot.DTO.SupplierSaveDataDTO;
import com.newproduct.springboot.entity.Product;
import com.newproduct.springboot.entity.Supplier;
import com.newproduct.springboot.mapper.SupplierMapper;
import com.newproduct.springboot.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/supplier")
public class SupplierController {
    private final SupplierMapper supplierMapper;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierMapper supplierMapper) {
        this.supplierMapper = supplierMapper;
    }

    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String supplierName){
        pageNum = (pageNum-1) * pageSize;
        supplierName = '%' + supplierName +'%';
        List<Supplier> data = supplierMapper.selectPage(pageNum,pageSize,supplierName);
        Integer total = supplierMapper.selectTotal(supplierName);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }

    @DeleteMapping("/api/delete/{id}")
    public Integer delete(@PathVariable Integer id ){
        return supplierMapper.deleteById(id);
    }

    @PostMapping("/saveData")
    public ResponseEntity<String> saveSupplierData(@RequestBody SupplierSaveDataDTO supplierSaveDataDTO) {
        try {
            // 调用供应商服务保存数据的方法
            supplierService.saveSupplierData(supplierSaveDataDTO);
            return ResponseEntity.ok("添加供应商成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("添加供应商失败");
        }
    }
}
