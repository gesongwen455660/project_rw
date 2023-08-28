package com.newproduct.springboot.mapper;

import com.newproduct.springboot.entity.Product;
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



    @Select("select * from product where (type = #{type} or #{type} is null or #{type} = '') AND sku like #{sku} limit #{pageNum}, #{pageSize}")
    //@Select("select * from product where type = #{type} AND sku like #{sku} limit #{pageNum}, #{pageSize}") //here sku like #{sku}
    List<Product> selectPage(Integer pageNum, Integer pageSize, String sku, String type); //String sku

    @Select("select count(id) from product where (type = #{type} or #{type} is null or #{type} = '') AND sku like concat('%' ,#{sku} ,'%')") //
    Integer selectTotal(String sku,String type); //String sku



}