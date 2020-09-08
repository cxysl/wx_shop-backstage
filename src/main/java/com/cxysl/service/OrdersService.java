package com.cxysl.service;

import com.cxysl.comm.Page;
import com.cxysl.dao.OrdersDAOImpl;
import com.cxysl.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
    @Autowired
    private OrdersDAOImpl dao;

    public Page<Orders> queryAll(Page<Orders> page){
        return  dao.queryAll(page);
    }

    public Orders getOrderOne(Integer id){
        return dao.getOrderOne(id);
    }

    public Orders getLastOne(){
        return dao.getLastOne();
    }

//    public Customer getCustomer(Integer cId){
//        return dao.getCustomer(cId);
//    }

    public int add_Order(Orders p){

        return dao.add_Order(p);
    }

    public int Update_Order(Orders p){

        return dao.Update_Order(p);
    }

    public int Update_OrderState(String state, Orders p){
        return dao.Update_OrderState(state,p);
    }

    public int Update_OrderSumPrice(Integer id){

        return dao.Update_OrderSumPrice(id);
    }

    public int del_Order(Integer id)
    {
        return dao.del_Order(id);
    }
}
