package com.newproduct.springboot.controller;

import com.newproduct.springboot.entity.*;
import com.newproduct.springboot.mapper.SimpleOrderMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/simpleOrder1") //part szvc
public class SimpleOrderController {

    @Resource
    private SimpleOrderMapper simpleOrderMapper;

    @GetMapping("/page") //szvc部分数据
    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String orderid, @RequestParam(required = false) String wcs1){
        pageNum = (pageNum-1) * pageSize;
        orderid = '%' + orderid +'%';
        wcs1 = (wcs1 != null) ? wcs1 : ""; //wcs1是区分仓库的字段
        List<SzvcOrderOutsea> data = simpleOrderMapper.selectPage(pageNum,pageSize,orderid,wcs1);
        //List<SzvcOrderOutsea> data = simpleOrderMapper.selectTest(pageNum,pageSize);
        Integer total = simpleOrderMapper.selectTotal(orderid,wcs1);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }


    @GetMapping("/pageTwo")//wfas部分数据
    public Map<String, Object> findPageTwo(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String ponumber, @RequestParam(required = false) String warehousename) {
        pageNum = (pageNum-1) * pageSize;
        ponumber = '%' + ponumber + '%';
        warehousename = (warehousename != null) ? warehousename : "";
        List<WfasOrderOutsea> data = simpleOrderMapper.selectPageTwo(pageNum,pageSize,ponumber,warehousename);
        Integer total = simpleOrderMapper.selectTotalTwo(ponumber,warehousename);
        Map<String, Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }

    @GetMapping("/pageThree")//wfrw部分数据
    public Map<String, Object> findPageThree(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String ponumber, @RequestParam(required = false) String warehousename) {
        pageNum = (pageNum-1) * pageSize;
        ponumber = '%' + ponumber + '%';
        warehousename = (warehousename != null) ? warehousename : "";
        List<WfrwOrderOutsea> data = simpleOrderMapper.selectPageThree(pageNum,pageSize,ponumber,warehousename);
        Integer total = simpleOrderMapper.selectTotalThree(ponumber,warehousename);
        Map<String, Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }

    @GetMapping("/pageFour")//zn部分数据
    public Map<String, Object> findPageFour(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String orderid) {
        pageNum = (pageNum-1) * pageSize;
        orderid = '%' + orderid + '%';
        List<ZnOrderOutsea> data = simpleOrderMapper.selectPageFour(pageNum,pageSize,orderid);
        Integer total = simpleOrderMapper.selectTotalFour(orderid);
        Map<String, Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }

    @GetMapping("/pageFive")//srw部分数据
    public Map<String, Object> findPageFive(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String retailerordernumber) {
        pageNum = (pageNum-1) * pageSize;
        retailerordernumber = '%' + retailerordernumber + '%';
        List<SrwOrderOutsea> data = simpleOrderMapper.selectPageFive(pageNum,pageSize,retailerordernumber);
        Integer total = simpleOrderMapper.selectTotalFive(retailerordernumber);
        Map<String, Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }

    @GetMapping("/pageAll")//所有数据
    public Map<String, Object> findPageAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam String orderid) {
        pageNum = (pageNum-1) * pageSize;
        orderid = '%' + orderid + '%';
        List<OrderOutsea> data = simpleOrderMapper.selectPageAll(pageNum,pageSize,orderid);
        Integer total = simpleOrderMapper.selectTotalAll(orderid);
        Map<String, Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }


}
