package com.itcast.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itcast.dao.IOrdersDao;
import com.itcast.domain.Orders;
import com.itcast.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IOrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page,int size) {
        //分页方法一定要在查询的上面才能生效
        PageHelper.startPage ( page, size );
        List<Orders> ordersList = ordersDao.findAll ();
        return ordersList;
    }

    @Override
    public Orders findById(String id) {
        Orders orders = ordersDao.findById ( id );
        return orders;
    }
}
