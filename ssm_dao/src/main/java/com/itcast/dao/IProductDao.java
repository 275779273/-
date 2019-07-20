package com.itcast.dao;

import com.itcast.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductDao {

    /**
     * 根据id查询产品
     * @param id
     * @return
     */
    @Select ( "select * from product where id=#{id}" )
    Product findProductById(String id);

    /**
     * 查询所有产品
     * @return
     */
    @Select ( "select * from product" )
    List<Product> findAll();

    /**
     * 添加产品
     * @param product
     */
    @Insert ( "insert into product (productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus}) " )
    void save(Product product);


}
