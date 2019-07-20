package com.itcast.dao;

import com.itcast.domain.Member;
import com.itcast.domain.Orders;
import com.itcast.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdersDao {

    @Select ( "select * from orders" )
    @Results({
            @Result(id = true,property = "id" ,column = "id" ),
            @Result(property = "orderNum" ,column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "ProductId",javaType = Product.class,
                    one = @One(select = "com.itcast.dao.IProductDao.findProductById" ))
    })
    List<Orders> findAll();


    @Select ( "select * from orders where id=#{id}" )
    @Results({
            @Result(id = true,property = "id" ,column = "id" ),
            @Result(property = "orderNum" ,column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "ProductId",javaType = Product.class,
                    one = @One(select = "com.itcast.dao.IProductDao.findProductById" )),
            @Result(property = "member",column = "memberId",javaType = Member.class,
                    one = @One(select = "com.itcast.dao.IMemberDao.findMemberById")),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "com.itcast.dao.ITravellerDao.findTravellerByOrderId"))
    })
    Orders findById(String id);
}
