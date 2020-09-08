package com.cxysl.service;

import com.cxysl.comm.Page;
import com.cxysl.dao.OrderDetailsDAOImpl;
import com.cxysl.entity.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailsDAOImpl dao;

    public Page<OrderDetails> queryAll(Page<OrderDetails> page){
        return  dao.queryAll(page);
    }


    public OrderDetails getOrderDetailsOne(Integer goodsId,Integer orderId){
        return dao.getOrderDetailsOne(goodsId,orderId);
    }
    public Page<OrderDetails> getOrderOne(Page<OrderDetails> page,Integer orderId){
        return  dao.getOrderOne(page,orderId);
    }

    public int add_OrderDetails(OrderDetails p){

        return dao.add_OrderDetails(p);
    }

    public int Update_OrderDetails(OrderDetails p){

        return dao.Update_OrderDetails(p);
    }

    public int del_OrderDetails(Integer goodsId,Integer orderId)
    {
        return dao.del_OrderDetails(goodsId,orderId);
    }


}
