package com.newproduct.springboot.mapper;

import com.newproduct.springboot.entity.Product;
import com.newproduct.springboot.entity.Supplier;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SupplierMapper {

//    @Select("select * from supplier")
//    List<Supplier> getAllSuppliers();

    @Delete("delete from supplier where id = #{id}")
    Integer deleteById(@Param("id") Integer id);


    @Select("select * from supplier where supplierName like #{supplierName}  limit #{pageNum}, #{pageSize}")
    List<Supplier> selectPage(Integer pageNum, Integer pageSize, String supplierName); //String sku

    @Select("select count(id) from supplier where  supplierName like concat('%' ,#{supplierName} ,'%')") //
    Integer selectTotal(String supplierName); //String sku

}
