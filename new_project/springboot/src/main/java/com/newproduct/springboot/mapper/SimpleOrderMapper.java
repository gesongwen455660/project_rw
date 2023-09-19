package com.newproduct.springboot.mapper;

import com.newproduct.springboot.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SimpleOrderMapper {

    @Select("select * from szvcorderoutsea where (wcs1 = #{wcs1} or #{wcs1} is null or #{wcs1} = '') AND orderId like #{orderId} limit #{pageNum}, #{pageSize}")
    List<SzvcOrderOutsea> selectPage(Integer pageNum, Integer pageSize, String orderId, String wcs1); //szvc查询数据库


    @Select("select count(id) from szvcorderoutsea where (wcs1 = #{wcs1} or #{wcs1} is null or #{wcs1} = '') AND orderId like #{orderId}")
    Integer selectTotal(String orderId,String wcs1);//szvc查询数据库

    @Select("select * from wfasorderoutsea where (warehousename = #{warehousename} or #{warehousename} is null or #{warehousename} = '') AND ponumber like #{ponumber} limit #{pageNum}, #{pageSize}")
    List<WfasOrderOutsea> selectPageTwo(Integer pageNum, Integer pageSize, String ponumber, String warehousename); //wfas查询数据库


    @Select("select count(id) from wfasorderoutsea where (warehousename = #{warehousename} or #{warehousename} is null or #{warehousename} = '') AND ponumber like #{ponumber}")
    Integer selectTotalTwo(String ponumber,String warehousename);//wfas查询数据库

    @Select("select * from wfrworderoutsea where (warehousename = #{warehousename} or #{warehousename} is null or #{warehousename} = '') AND ponumber like #{ponumber} limit #{pageNum}, #{pageSize}")
    List<WfrwOrderOutsea> selectPageThree(Integer pageNum, Integer pageSize, String ponumber, String warehousename); //wfrw查询数据库


    @Select("select count(id) from wfrworderoutsea where (warehousename = #{warehousename} or #{warehousename} is null or #{warehousename} = '') AND ponumber like #{ponumber}")
    Integer selectTotalThree(String ponumber,String warehousename);//wfrw查询数据库

    @Select("select * from znorderoutsea where orderid like #{orderid} limit #{pageNum}, #{pageSize}")
    List<ZnOrderOutsea> selectPageFour(Integer pageNum, Integer pageSize, String orderid); //zn查询数据库


    @Select("select count(id) from znorderoutsea where orderid like #{orderid}")
    Integer selectTotalFour(String orderid);//zn查询数据库

    @Select("select * from srworderoutsea where retailerordernumber like #{retailerordernumber} limit #{pageNum}, #{pageSize}")
    List<SrwOrderOutsea> selectPageFive(Integer pageNum, Integer pageSize, String retailerordernumber); //srw查询数据库

    @Select("select count(id) from srworderoutsea where retailerordernumber like #{retailerordernumber}")
    Integer selectTotalFive(String retailerordernumber);//srw查询数据库

    @Select("select * from orderoutsea where orderid like #{orderid} order by id desc limit #{pageNum}, #{pageSize}")
    List<OrderOutsea> selectPageAll(Integer pageNum, Integer pageSize, String orderid); //查询所有

    @Select("select count(orderid) from orderoutsea where orderid like #{orderid}")
    Integer selectTotalAll(String orderid);//查询所有


//    @Insert("INSERT INTO wfasOrderOutsea () VALUES (#{}, #{}, #{})")
//    void insertData(@Param("column1") String column1, @Param("column2") String column2, @Param("column3") String column3);//导入wfas数据的sql
    @Insert("<script>" +
        "INSERT INTO wfasorderoutsea (" +
        "<foreach item='key' index='index' collection='data' separator=','>" +
        "`${key}`" + // 使用反引号将字段名括起来
        "</foreach>)" +
        " VALUES (" +
        "<foreach item='item' index='index' collection='data' separator=','>" +
        "#{item}" +
        "</foreach>)" +
        "</script>")
    void insertData(@Param("data") Map<String, Object> data);
}
