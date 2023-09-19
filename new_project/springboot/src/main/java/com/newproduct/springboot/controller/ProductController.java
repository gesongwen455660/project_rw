package com.newproduct.springboot.controller;


import com.newproduct.springboot.DTO.UpdateDataDTO;
import com.newproduct.springboot.Repository.ProductLogRepository;
import com.newproduct.springboot.Repository.ProductRepository;
import com.newproduct.springboot.entity.Product;
import com.newproduct.springboot.entity.ProductLog;
import com.newproduct.springboot.entity.ProductOld;
import com.newproduct.springboot.mapper.ProductMapper;
import com.newproduct.springboot.mapper.SimpleOrderMapper;
import com.newproduct.springboot.service.ProductService;
import jakarta.annotation.Resource;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    @Resource
    private SimpleOrderMapper simpleOrderMapper;

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
    public String updateData(@ModelAttribute UpdateDataDTO updateDataDTO) throws IOException {
            Product product = productRepository.findById(updateDataDTO.getId()).orElse(null);
            ProductLog productlog = new ProductLog();
            //----------------------更新日志部分
            // 获取当前时间
            Date currentTime = new Date();

            // 将时间转换为字符串��式
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = dateFormat.format(currentTime);

//            productlog.setProductid(id);
//            productlog.setSku(skuOld);
            productlog.setProductid(updateDataDTO.getId());
            productlog.setFactory(updateDataDTO.getFactory());
            productlog.setSku(updateDataDTO.getSku());
            productlog.setFba(updateDataDTO.getFba());
            productlog.setFbm(updateDataDTO.getFbm());
            productlog.setFulfillmentfeeamazon(updateDataDTO.getFulfillmentfeeamazon());
            productlog.setFulfillmentfeeoutsea(updateDataDTO.getFulfillmentfeeoutsea());
            productlog.setProductlenghcm1(updateDataDTO.getProductlenghcm1());
            productlog.setProductlenghcm2(updateDataDTO.getProductlenghcm2());
            productlog.setProductshortlenghcm(updateDataDTO.getProductshortlenghcm());
            productlog.setOutboxlenghcm1(updateDataDTO.getOutboxlenghcm1());
            productlog.setOutboxlenghcm2(updateDataDTO.getOutboxlenghcm2());
            productlog.setOutboxshortlenghcm(updateDataDTO.getOutboxshortlenghcm());
            productlog.setWeightkg1(updateDataDTO.getWeightkg1());
            productlog.setWeightkg2(updateDataDTO.getWeightkg2());
            productlog.setProductlenghinch1(updateDataDTO.getProductlenghinch1());
            productlog.setProductlenghinch2(updateDataDTO.getProductlenghinch2());
            productlog.setProductshortlenghinch(updateDataDTO.getProductshortlenghinch());
            productlog.setOutboxlenghinch1(updateDataDTO.getOutboxlenghinch1());
            productlog.setOutboxlenghinch2(updateDataDTO.getOutboxlenghinch2());
            productlog.setOutboxshortlenghinch(updateDataDTO.getOutboxshortlenghinch());
            productlog.setWeightlb1(updateDataDTO.getWeightlb1());
            productlog.setWeightlb2(updateDataDTO.getWeightlb2());
            productlog.setQuantityhq(updateDataDTO.getQuantityhq());
            productlog.setQuantity(updateDataDTO.getQuantity());
            productlog.setInboxlenghinch1(updateDataDTO.getInboxlenghinch1());
            productlog.setInboxlenghinch2(updateDataDTO.getInboxlenghinch2());
            productlog.setInboxshortlenghinch(updateDataDTO.getInboxshortlenghinch());
            productlog.setInboxweightlb1(updateDataDTO.getInboxweightlb1());
            productlog.setInboxweightlb2(updateDataDTO.getInboxweightlb2());
            productlog.setInboxlenghcm1(updateDataDTO.getInboxlenghcm1());
            productlog.setInboxlenghcm2(updateDataDTO.getInboxlenghcm2());
            productlog.setInboxshortlenghcm(updateDataDTO.getInboxshortlenghcm());
            productlog.setInboxweightkg1(updateDataDTO.getInboxweightkg1());
            productlog.setInboxweightkg2(updateDataDTO.getInboxweightkg2());
            productlog.setMinlenghinchheight(updateDataDTO.getMinlenghinchheight());
            productlog.setMinlenghinchwidth(updateDataDTO.getMinlenghinchwidth());
            productlog.setMinlenghinchhigh(updateDataDTO.getMinlenghinchhigh());
            productlog.setMinweight1(updateDataDTO.getMinweight1());
            productlog.setMinweight2(updateDataDTO.getMinweight2());
            productlog.setType(updateDataDTO.getType());
            productlog.setTimestamp(formattedDate);
            productLogRepository.save(productlog);

            //-----------------------更新新数据
            MultipartFile file1 = updateDataDTO.getFileUpdate1();
            MultipartFile file2 = updateDataDTO.getFileUpdate2();
            MultipartFile file3 = updateDataDTO.getFileUpdate3();

            String uploadDir = "D:/project/vue/public/image";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs(); // 如果保存路径不存在，则创建该路径
            }

            if (file1 != null && !file1.isEmpty()) {
                String fileName1 = file1.getOriginalFilename();
                File destFile1 = new File(uploadDir + File.separator + fileName1);
                file1.transferTo(destFile1);
                product.setUrl1(fileName1);
            }

            if (file2 != null && !file2.isEmpty()) {
                String fileName2 = file2.getOriginalFilename();
                File destFile2 = new File(uploadDir + File.separator + fileName2);
                file2.transferTo(destFile2);
                product.setUrl2(fileName2);
            }

            if (file3 != null && !file3.isEmpty()) {
                String fileName3 = file3.getOriginalFilename();
                File destFile3 = new File(uploadDir + File.separator + fileName3);
                file3.transferTo(destFile3);
                product.setUrl3(fileName3);
            }

            if(product != null) {
                if( updateDataDTO.getFactoryUpdate()!= null && !updateDataDTO.getFactoryUpdate().isEmpty()){
                    product.setSku(updateDataDTO.getFactoryUpdate());
                }
                if (updateDataDTO.getSkuUpdate() != null && !updateDataDTO.getSkuUpdate().isEmpty()) {
                    product.setSku(updateDataDTO.getSkuUpdate());
                }

                if (updateDataDTO.getFbaUpdate() != null && !updateDataDTO.getFbaUpdate().isEmpty()) {
                    product.setFba(updateDataDTO.getFbaUpdate());
                }

                if (updateDataDTO.getFbmUpdate() != null && !updateDataDTO.getFbmUpdate().isEmpty()) {
                    product.setFbm(updateDataDTO.getFbmUpdate());
                }

                if (updateDataDTO.getFulfillmentfeeamazonUpdate() != null && !updateDataDTO.getFulfillmentfeeamazonUpdate().isEmpty()) {
                    product.setFulfillmentfeeamazon(updateDataDTO.getFulfillmentfeeamazonUpdate());
                }

                if (updateDataDTO.getFulfillmentfeeoutseaUpdate() != null && !updateDataDTO.getFulfillmentfeeoutseaUpdate().isEmpty()) {
                    product.setFulfillmentfeeoutsea(updateDataDTO.getFulfillmentfeeoutseaUpdate());
                }

                if (updateDataDTO.getProductlenghcm1Update() != null && !updateDataDTO.getProductlenghcm1Update().isEmpty()) {
                    product.setProductlenghcm1(updateDataDTO.getProductlenghcm1Update());
                }

                if (updateDataDTO.getProductlenghcm2Update() != null && !updateDataDTO.getProductlenghcm2Update().isEmpty()) {
                    product.setProductlenghcm2(updateDataDTO.getProductlenghcm2Update());
                }
                if (updateDataDTO.getProductshortlenghcmUpdate() != null && !updateDataDTO.getProductshortlenghcmUpdate().isEmpty()) {
                    product.setProductshortlenghcm(updateDataDTO.getProductshortlenghcmUpdate());
                }

                if (updateDataDTO.getOutboxlenghcm1Update() != null && !updateDataDTO.getOutboxlenghcm1Update().isEmpty()) {
                    product.setOutboxlenghcm1(updateDataDTO.getOutboxlenghcm1Update());
                }

                if (updateDataDTO.getOutboxlenghcm2Update() != null && !updateDataDTO.getOutboxlenghcm2Update().isEmpty()) {
                    product.setOutboxlenghcm2(updateDataDTO.getOutboxlenghcm2Update());
                }

                if (updateDataDTO.getOutboxshortlenghcmUpdate() != null && !updateDataDTO.getOutboxshortlenghcmUpdate().isEmpty()) {
                    product.setOutboxshortlenghcm(updateDataDTO.getOutboxshortlenghcmUpdate());
                }

                if (updateDataDTO.getWeightkg1Update() != null && !updateDataDTO.getWeightkg1Update().isEmpty()) {
                    product.setWeightkg1(updateDataDTO.getWeightkg1Update());
                }

                if (updateDataDTO.getWeightkg2Update() != null && !updateDataDTO.getWeightkg2Update().isEmpty()) {
                    product.setWeightkg2(updateDataDTO.getWeightkg2Update());
                }

                if (updateDataDTO.getProductlenghinch1Update() != null && !updateDataDTO.getProductlenghinch1Update().isEmpty()) {
                    product.setProductlenghinch1(updateDataDTO.getProductlenghinch1Update());
                }

                if (updateDataDTO.getProductlenghinch2Update() != null && !updateDataDTO.getProductlenghinch2Update().isEmpty()) {
                    product.setProductlenghinch2(updateDataDTO.getProductlenghinch2Update());
                }

                if (updateDataDTO.getProductshortlenghinchUpdate() != null && !updateDataDTO.getProductshortlenghinchUpdate().isEmpty()) {
                    product.setProductshortlenghinch(updateDataDTO.getProductshortlenghinchUpdate());
                }

                if (updateDataDTO.getOutboxlenghinch1Update() != null && !updateDataDTO.getOutboxlenghinch1Update().isEmpty()) {
                    product.setOutboxlenghinch1(updateDataDTO.getOutboxlenghinch1Update());
                }

                if (updateDataDTO.getOutboxlenghinch2Update() != null && !updateDataDTO.getOutboxlenghinch2Update().isEmpty()) {
                    product.setOutboxlenghinch2(updateDataDTO.getOutboxlenghinch2Update());
                }

                if (updateDataDTO.getOutboxshortlenghinchUpdate() != null && !updateDataDTO.getOutboxshortlenghinchUpdate().isEmpty()) {
                    product.setOutboxshortlenghinch(updateDataDTO.getOutboxshortlenghinchUpdate());
                }
                if (updateDataDTO.getWeightlb1Update() != null && !updateDataDTO.getWeightlb1Update().isEmpty()) {
                    product.setWeightlb1(updateDataDTO.getWeightlb1Update());
                }

                if (updateDataDTO.getWeightlb2Update() != null && !updateDataDTO.getWeightlb2Update().isEmpty()) {
                    product.setWeightlb2(updateDataDTO.getWeightlb2Update());
                }

                if (updateDataDTO.getQuantityhqUpdate() != null && !updateDataDTO.getQuantityhqUpdate().isEmpty()) {
                    product.setQuantityhq(updateDataDTO.getQuantityhqUpdate());
                }

                if (updateDataDTO.getQuantityUpdate() != null && !updateDataDTO.getQuantityUpdate().isEmpty()) {
                    product.setQuantity(updateDataDTO.getQuantityUpdate());
                }

                if (updateDataDTO.getInboxlenghinch1Update() != null && !updateDataDTO.getInboxlenghinch1Update().isEmpty()) {
                    product.setInboxlenghinch1(updateDataDTO.getInboxlenghinch1Update());
                }

                if (updateDataDTO.getInboxlenghinch2Update() != null && !updateDataDTO.getInboxlenghinch2Update().isEmpty()) {
                    product.setInboxlenghinch2(updateDataDTO.getInboxlenghinch2Update());
                }

                if (updateDataDTO.getInboxshortlenghinchUpdate() != null && !updateDataDTO.getInboxshortlenghinchUpdate().isEmpty()) {
                    product.setInboxshortlenghinch(updateDataDTO.getInboxshortlenghinchUpdate());
                }

                if (updateDataDTO.getInboxweightlb1Update() != null && !updateDataDTO.getInboxweightlb1Update().isEmpty()) {
                    product.setInboxweightlb1(updateDataDTO.getInboxweightlb1Update());
                }

                if (updateDataDTO.getInboxweightlb2Update() != null && !updateDataDTO.getInboxweightlb2Update().isEmpty()) {
                    product.setInboxweightlb2(updateDataDTO.getInboxweightlb2Update());
                }

                if (updateDataDTO.getInboxlenghcm1Update() != null && !updateDataDTO.getInboxlenghcm1Update().isEmpty()) {
                    product.setInboxlenghcm1(updateDataDTO.getInboxlenghcm1Update());
                }

                if (updateDataDTO.getInboxlenghcm2Update() != null && !updateDataDTO.getInboxlenghcm2Update().isEmpty()) {
                    product.setInboxlenghcm2(updateDataDTO.getInboxlenghcm2Update());
                }

                if (updateDataDTO.getInboxshortlenghcmUpdate() != null && !updateDataDTO.getInboxshortlenghcmUpdate().isEmpty()) {
                    product.setInboxshortlenghcm(updateDataDTO.getInboxshortlenghcmUpdate());
                }

                if (updateDataDTO.getInboxweightkg1Update() != null && !updateDataDTO.getInboxweightkg1Update().isEmpty()) {
                    product.setInboxweightkg1(updateDataDTO.getInboxweightkg1Update());
                }

                if (updateDataDTO.getInboxweightkg2Update() != null && !updateDataDTO.getInboxweightkg2Update().isEmpty()) {
                    product.setInboxweightkg2(updateDataDTO.getInboxweightkg2Update());
                }

                if (updateDataDTO.getMinlenghinchheightUpdate() != null && !updateDataDTO.getMinlenghinchheightUpdate().isEmpty()) {
                    product.setMinlenghinchheight(updateDataDTO.getMinlenghinchheightUpdate());
                }

                if (updateDataDTO.getMinlenghinchwidthUpdate() != null && !updateDataDTO.getMinlenghinchwidthUpdate().isEmpty()) {
                    product.setMinlenghinchwidth(updateDataDTO.getMinlenghinchwidthUpdate());
                }

                if (updateDataDTO.getMinlenghinchhighUpdate() != null && !updateDataDTO.getMinlenghinchhighUpdate().isEmpty()) {
                    product.setMinlenghinchhigh(updateDataDTO.getMinlenghinchhighUpdate());
                }

                if (updateDataDTO.getMinweight1Update() != null && !updateDataDTO.getMinweight1Update().isEmpty()) {
                    product.setMinweight1(updateDataDTO.getMinweight1Update());
                }

                if (updateDataDTO.getMinweight2Update() != null && !updateDataDTO.getMinweight2Update().isEmpty()) {
                    product.setMinweight2(updateDataDTO.getMinweight2Update());
                }

                if (updateDataDTO.getTypeUpdate() != null && !updateDataDTO.getTypeUpdate().isEmpty()) {
                    product.setType(updateDataDTO.getTypeUpdate());
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
    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String sku, @RequestParam String factory, @RequestParam(required = false) String type){
        pageNum = (pageNum-1) * pageSize;
        sku = '%' + sku +'%';
        factory = '%' + factory +'%';
        type = (type != null) ? type : "";
        List<Product> data = productMapper.selectPage(pageNum,pageSize,sku,factory,type);
        Integer total = productMapper.selectTotal(sku,factory,type);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }

    @GetMapping("/pageBase")
    public Map<String, Object> findPageBase(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String sku, @RequestParam(required = false) String type){
        pageNum = (pageNum-1) * pageSize;
        sku = '%' + sku +'%';
        type = (type != null) ? type : "";
        List<ProductOld> data = productMapper.selectPageBase(pageNum,pageSize,sku,type);
        Integer total = productMapper.selectTotalBase(sku,type);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }

    @PostMapping("/api/importExcel")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        List<Map<String, Object>> data = readExcelFile(file);
        for (Map<String, Object> row : data) {
            simpleOrderMapper.insertData(row);
            System.out.print(row);
        }
        return "redirect:/";
    }
    public List<Map<String, Object>> readExcelFile(MultipartFile file) {
        List<Map<String, Object>> list = new ArrayList<>();
        try (InputStream is = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                Map<String, Object> data = new HashMap<>();
                for (int cellIndex = 0; cellIndex < headerRow.getLastCellNum(); cellIndex++) {
                    Cell cell = row.getCell(cellIndex);
                    String columnName = headerRow.getCell(cellIndex).getStringCellValue();
                    if (columnName == null || columnName.trim().isEmpty()) {
                        continue; // Skip this cell if the column name is null or empty
                    }
                    String value = null;
                    if (cell != null) {
                        cell.setCellType(CellType.STRING); // 强制将单元格类型设置为字符串
                        value = cell.getStringCellValue();
                    }
                    data.put(columnName, value);
                }
                list.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}