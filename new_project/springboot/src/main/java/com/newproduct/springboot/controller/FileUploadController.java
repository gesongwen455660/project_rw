package com.newproduct.springboot.controller;

import com.newproduct.springboot.Repository.FileRepository;
import com.newproduct.springboot.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
public class FileUploadController {

    private final FileRepository fileRepository;

    @Autowired
    public FileUploadController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @PostMapping("/api/saveData")
    public String saveData(@RequestParam("file") MultipartFile file,
                           @RequestParam(value = "sku",required = false) String sku,
                           @RequestParam(value = "sku13",required = false) String sku13,
                           @RequestParam(value = "color",required = false) String color,
                           @RequestParam(value = "chicun1",required = false) String chicun1,
                           @RequestParam(value = "chicun2",required = false) String chicun2,
                           @RequestParam(value = "weight1",required = false) String weight1,
                           @RequestParam(value = "weight2",required = false) String weight2,
                           @RequestParam(value = "input",required = false) String input,
                           @RequestParam(value = "quantity",required = false) String quantityHQ,
                           @RequestParam(value = "type",required = false) String type) {
        if (file.isEmpty()) {
            // 文件为空，处理错误逻辑
            return "error";
        }

        try {
            // 生成唯一文件名
            //String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String fileName = file.getOriginalFilename();
            // 设置保存路径
            String uploadDir = "D:/project/vue/public/image";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs(); // 如果保存路径不存在，则创建该路径
            }

            // 构造文件对象
            File destFile = new File(uploadDir + File.separator + fileName);

            // 保存文件
            file.transferTo(destFile);

            // 创建Product对象并保存到数据库
            Product product = new Product();
            product.setUrl(fileName); // 设置URL为相对路径，即"assets/文件名"
            product.setSku(sku);
            product.setSku13(sku13);
            product.setColor(color);
            product.setChicun1(chicun1);
            product.setChicun2(chicun2);
            product.setWeight1(weight1);
            product.setWeight2(weight2);
            product.setInput(input);
            product.setQuantityHQ(quantityHQ);
            product.setType(type);
            fileRepository.save(product);

            // 处理保存成功的逻辑
            return "success";
        } catch (IOException e) {
            // 处理保存失败的逻辑
            return "error";
        }
    }
}