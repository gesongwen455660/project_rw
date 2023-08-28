package com.newproduct.springboot.controller;


import com.newproduct.springboot.Repository.ProductLogRepository;
import com.newproduct.springboot.Repository.ProductRepository;
import com.newproduct.springboot.entity.Product;
import com.newproduct.springboot.entity.ProductLog;
import com.newproduct.springboot.mapper.ProductMapper;
import com.newproduct.springboot.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    private final ProductRepository productRepository;
    @Autowired
    public ProductController(ProductRepository productRepository, ProductLogRepository productLogRepository) {
        this.productRepository = productRepository;
        this.productLogRepository = productLogRepository;
    }

    private final ProductLogRepository productLogRepository;


    //新增或更改
//    @PostMapping
//    public Integer save(@RequestBody Product product){
//        return productService.save(product);
//    }

    //查询所有 全部产品
    @GetMapping
    public List<Product> index(){
        return productMapper.findall();
    }


    @PostMapping("/api/updateProduct")
    public String updateData(@RequestParam(value = "id",required = false) Integer id,
                             @RequestParam(value = "skuNew",required = false) String skuNew,
                             @RequestParam(value = "sku13New",required = false) String sku13New,
                             @RequestParam(value = "colorNew",required = false) String colorNew,
                             @RequestParam(value = "chicun1New",required = false) String chicun1New,
                             @RequestParam(value = "chicun2New",required = false) String chicun2New,
                             @RequestParam(value = "weight1New",required = false) String weight1New,
                             @RequestParam(value = "weight2New",required = false) String weight2New,
                             @RequestParam(value = "inputNew",required = false) String inputNew,
                             @RequestParam(value = "quantityNew",required = false) String quantityHQNew,
                             @RequestParam(value = "typeNew",required = false) String typeNew,

                             @RequestParam(value = "skuOld",required = false) String skuOld,
                             @RequestParam(value = "sku13Old",required = false) String sku13Old,
                             @RequestParam(value = "colorOld",required = false) String colorOld,
                             @RequestParam(value = "chicun1Old",required = false) String chicun1Old,
                             @RequestParam(value = "chicun2Old",required = false) String chicun2Old,
                             @RequestParam(value = "weight1Old",required = false) String weight1Old,
                             @RequestParam(value = "weight2Old",required = false) String weight2Old,
                             @RequestParam(value = "inputOld",required = false) String inputOld,
                             @RequestParam(value = "quantityOld",required = false) String quantityHQOld,
                             @RequestParam(value = "typeOld",required = false) String typeOld) {
            Product product = productRepository.findById(id).orElse(null);
            ProductLog productlog = new ProductLog();
            // 获取当前时间
            Date currentTime = new Date();

            // 将时间转换为字符串��式
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(currentTime);
            productlog.setProductid(id);
            productlog.setSku(skuOld);
            productlog.setSku13(sku13Old);
            productlog.setColor(colorOld);
            productlog.setChicun1(chicun1Old);
            productlog.setChicun2(chicun2Old);
            productlog.setWeight1(weight1Old);
            productlog.setWeight2(weight2Old);
            productlog.setType(typeOld);
            productlog.setInput(inputOld);
            productlog.setQuantityHQ(quantityHQOld);
            productlog.setTimestamp(formattedDate);
            productLogRepository.save(productlog);
            if(product != null) {
                if(skuNew != null && !skuNew.isEmpty()){
                    product.setSku(skuNew);
                }
                if(sku13New != null && !sku13New.isEmpty()){
                    product.setSku13(sku13New);
                }
                if(colorNew != null && !colorNew.isEmpty()){
                    product.setColor(colorNew);
                }
                if(chicun1New != null && !chicun1New.isEmpty()){
                    product.setChicun1(chicun1New);
                }
                if(chicun2New != null && !chicun2New.isEmpty()){
                    product.setChicun2(chicun2New);
                }
                if(weight1New != null && !weight1New.isEmpty()){
                    product.setWeight1(weight1New);
                }
                if(weight2New != null && !weight2New.isEmpty()){
                    product.setWeight2(weight2New);
                }
                if(inputNew != null && !inputNew.isEmpty()){
                    product.setInput(inputNew);
                }
                if(quantityHQNew != null && !quantityHQNew.isEmpty()){
                    product.setQuantityHQ(quantityHQNew);
                }
                if(typeNew != null && !typeNew.isEmpty()){
                    product.setType(typeNew);
                }
                productRepository.save(product);
                return "success";
            }else{
                return "error";
            }



    }



    //根据sku或者id删除
    @DeleteMapping("/api/delete/{id}")
    public Integer delete(@PathVariable Integer id ){
        return productMapper.deleteById(id);
    }

    //分页查询
    //pageNum为sql limit第一个参数 pageSize 为sql limit第二个参数 公式为 参数1=（pageNum-1）*pageSize 参数2=pageSize
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String sku, @RequestParam(required = false) String type){
        pageNum = (pageNum-1) * pageSize;
        sku = '%' + sku +'%';
        type = (type != null) ? type : "";
        List<Product> data = productMapper.selectPage(pageNum,pageSize,sku,type);
        Integer total = productMapper.selectTotal(sku,type);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }
}