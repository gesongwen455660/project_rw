package com.newproduct.springboot.controller;

//import com.newproduct.springboot.service.ProductLogService;
import com.newproduct.springboot.entity.ProductLog;
import com.newproduct.springboot.mapper.ProductLogMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductLogController {

    //private final ProductLogService productLogService;

    //@Autowired
    //public ProductLogController(ProductLogService productLogService) {
        //this.productLogService = productLogService;
    //}

//    @GetMapping("/api/productlogs/{id}")
//    public List<ProductLog> getProductLogsByProductId(@PathVariable("id") Integer productId){
//        return productLogService.getProductLogsByProductId(productId);
//    }
    @Resource
    private ProductLogMapper productLogMapper;

    @GetMapping("/api/productlogs/{productid}")
    public Map<String,Object> findProductLogs(@PathVariable Integer productid){
            List<ProductLog> data = productLogMapper.selectProductLogByProductid(productid);
            Map<String,Object> res = new HashMap<>();
            res.put("data",data);
            return res;
    }





}
