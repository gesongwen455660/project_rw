package com.newproduct.springboot.controller;

import com.newproduct.springboot.Repository.FileRepository;
import com.newproduct.springboot.entity.Product;
import com.newproduct.springboot.DTO.SaveDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
public class FileUploadController {

    private final FileRepository fileRepository;

    @Autowired
    public FileUploadController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @PostMapping("/api/saveData")
    public String saveData(@ModelAttribute SaveDataDTO saveDataDTO) {
        MultipartFile file1 = saveDataDTO.getFile1();
        MultipartFile file2 = saveDataDTO.getFile2();
        MultipartFile file3 = saveDataDTO.getFile3();
        if (file1.isEmpty() || file2.isEmpty()) {
            // 文件为空，处理错误逻辑
            return "error";
        }

        try {
            // 生成唯一文件名
            //String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String fileName1 = file1.getOriginalFilename();
            String fileName2 = file2.getOriginalFilename();
            String fileName3 = file3.getOriginalFilename();
            // 设置保存路径
            String uploadDir = "D:/project/vue/public/image";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs(); // 如果保存路径不存在，则创建该路径
            }

            // 构造文件对象
            File destFile1 = new File(uploadDir + File.separator + fileName1);
            File destFile2 = new File(uploadDir + File.separator + fileName2);
            File destFile3 = new File(uploadDir + File.separator + fileName3);

            // 保存文件
            file1.transferTo(destFile1);
            file2.transferTo(destFile2);
            file3.transferTo(destFile3);

            // 创建Product对象并保存到数据库
            Product product = new Product();
            product.setUrl1(fileName1); // 设置URL为相对路径，即"assets/文件名"
            product.setUrl2(fileName2);
            product.setUrl3(fileName3);
            product.setFactory(saveDataDTO.getFactorySubmit());
            product.setSku(saveDataDTO.getSkuSubmit());
            product.setFba(saveDataDTO.getFbaSubmit());
            product.setFbm(saveDataDTO.getFbmSubmit());
            product.setFulfillmentfeeamazon(saveDataDTO.getFulfillmentfeeamazonSubmit());
            product.setFulfillmentfeeoutsea(saveDataDTO.getFulfillmentfeeoutseaSubmit());
            product.setProductlenghcm1(saveDataDTO.getProductlenghcm1Submit());
            product.setProductlenghcm2(saveDataDTO.getProductlenghcm2Submit());
            product.setProductshortlenghcm(saveDataDTO.getProductshortlenghcmSubmit());
            product.setOutboxlenghcm1(saveDataDTO.getOutboxlenghcm1Submit());
            product.setOutboxlenghcm2(saveDataDTO.getOutboxlenghcm2Submit());
            product.setOutboxshortlenghcm(saveDataDTO.getOutboxshortlenghcmSubmit());
            product.setWeightkg1(saveDataDTO.getWeightkg1Submit());
            product.setWeightkg2(saveDataDTO.getWeightkg2Submit());
            product.setProductlenghinch1(saveDataDTO.getProductlenghinch1Submit());
            product.setProductlenghinch2(saveDataDTO.getProductlenghinch2Submit());
            product.setProductshortlenghinch(saveDataDTO.getProductshortlenghinchSubmit());
            product.setOutboxlenghinch1(saveDataDTO.getOutboxlenghinch1Submit());
            product.setOutboxlenghinch2(saveDataDTO.getOutboxlenghinch2Submit());
            product.setOutboxshortlenghinch(saveDataDTO.getOutboxshortlenghinchSubmit());
            product.setWeightlb1(saveDataDTO.getWeightlb1Submit());
            product.setWeightlb2(saveDataDTO.getWeightlb2Submit());
            product.setQuantityhq(saveDataDTO.getQuantityhqSubmit());
            product.setQuantity(saveDataDTO.getQuantitySubmit());
            product.setInboxlenghinch1(saveDataDTO.getInboxlenghinch1Submit());
            product.setInboxlenghinch2(saveDataDTO.getInboxlenghinch2Submit());
            product.setInboxshortlenghinch(saveDataDTO.getInboxshortlenghinchSubmit());
            product.setInboxweightlb1(saveDataDTO.getInboxweightlb1Submit());
            product.setInboxweightlb2(saveDataDTO.getInboxweightlb2Submit());
            product.setInboxlenghcm1(saveDataDTO.getInboxlenghcm1Submit());
            product.setInboxlenghcm2(saveDataDTO.getInboxlenghcm2Submit());
            product.setInboxshortlenghcm(saveDataDTO.getInboxshortlenghcmSubmit());
            product.setInboxweightkg1(saveDataDTO.getInboxweightkg1Submit());
            product.setInboxweightkg2(saveDataDTO.getInboxweightkg2Submit());
            product.setMinlenghinchheight(saveDataDTO.getMinlenghinchheightSubmit());
            product.setMinlenghinchwidth(saveDataDTO.getMinlenghinchwidthSubmit());
            product.setMinlenghinchhigh(saveDataDTO.getMinlenghinchhighSubmit());
            product.setMinweight1(saveDataDTO.getMinweight1Submit());
            product.setMinweight2(saveDataDTO.getMinweight2Submit());
            product.setType(saveDataDTO.getTypeSubmit());



            fileRepository.save(product);

            // 处理保存成功的逻辑
            return "success";
        } catch (IOException e) {
            // 处理保存失败的逻辑
            return "error";
        }
    }
}