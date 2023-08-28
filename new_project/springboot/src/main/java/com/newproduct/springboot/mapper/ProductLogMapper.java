package com.newproduct.springboot.mapper;

import com.newproduct.springboot.entity.ProductLog;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductLogMapper {


    @Select("select * from productlogs where productid = #{productid}")
    List<ProductLog> selectProductLogByProductid(Integer productid);
}
