package com.itcast.service;

import com.itcast.domain.Orders;

import java.util.List;


public interface IOrdersService {

    /**
     * 查询所有订单
     * @return
     */
    List<Orders> findAll(int page,int size);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Orders findById(String id);
}
