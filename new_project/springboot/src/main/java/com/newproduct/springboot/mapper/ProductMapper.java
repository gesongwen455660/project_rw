package com.newproduct.springboot.mapper;

import com.newproduct.springboot.entity.Product;
import com.newproduct.springboot.entity.ProductOld;
import org.apache.ibatis.annotations.*;

import java.util.List;
//@Mapper
public interface ProductMapper {


    @Select("select * from product")
    List<Product> findall();

    @Insert("insert into product(id,sku,sku13) VALUES (#{id}, #{sku}, #{sku13})")
    int insert(Product product);

    //    @Update("update product set url=#{url} , sku=#{sku},sku13=#{sku13} where sku = #{sku}")
    int update(Product product);

    @Delete("delete from product where id = #{id}")
    Integer deleteById(@Param("id") Integer id);



    @Select("select * from product where (type = #{type} or #{type} is null or #{type} = '') AND sku like #{sku} AND factory like concat('%' ,#{factory} ,'%') limit #{pageNum}, #{pageSize}")
    List<Product> selectPage(Integer pageNum, Integer pageSize, String sku, String factory, String type); //String sku

    @Select("select count(id) from product where (type = #{type} or #{type} is null or #{type} = '') AND sku like concat('%' ,#{sku} ,'%') AND factory like concat('%' ,#{factory} ,'%')") //
    Integer selectTotal(String sku, String factory, String type); //String sku


    @Select("select * from productold where (type = #{type} or #{type} is null or #{type} = '') AND sku like #{sku}  limit #{pageNum}, #{pageSize}")
    List<ProductOld> selectPageBase(Integer pageNum, Integer pageSize, String sku, String type); //String sku

    @Select("select count(id) from productold where (type = #{type} or #{type} is null or #{type} = '') AND sku like concat('%' ,#{sku} ,'%')") //
    Integer selectTotalBase(String sku,String type); //String sku


}