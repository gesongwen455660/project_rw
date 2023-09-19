package com.newproduct.springboot.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/download")
public class FileDownloadController {

    @GetMapping("/simpleOrder/{orderNumber}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String orderNumber) {
        // 根据订单号获取文件的路径
        String filePath = "D:\\project\\downloadFile\\" + orderNumber + ".pdf"; // 根据实际情况修改
        File file = new File(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok().headers(headers).body(resource);
    }
}