package com.itcast.dao;

import com.itcast.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITravellerDao {

    /**
     * 查询一个订单中所有的旅客
     * @param id
     * @return
     */
    @Select ( "select * from traveller where id in(select travellerId from order_traveller where orderId=#{id})" )
    List<Traveller> findTravellerByOrderId(String id);
}
