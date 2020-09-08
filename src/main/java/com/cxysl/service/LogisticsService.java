package com.cxysl.service;

import com.cxysl.comm.Page;
import com.cxysl.dao.LogisticsDAOImpl;
import com.cxysl.entity.Logistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticsService {

    @Autowired
    private LogisticsDAOImpl dao;

    public Page<Logistics> queryAll(Page<Logistics> page){
        return  dao.queryAll(page);
    }


    public Logistics getLogisticsOne(String id){
        return dao.getLogisticsOne(id);
    }

    public Logistics getLogisticsOne2(int orderId){
        return dao.getLogisticsOne2(orderId);
    }

    public int add_Logistics(Logistics p){

        return dao.add_Logistics(p);
    }

    public int Update_LogisticsState(String state,Logistics p){

        return dao.Update_LogisticsState(state,p);
    }

    public int Update_LogisticsNowAddress(String Address,Logistics p){

        return dao.Update_LogisticsNowAddress(Address,p);
    }

    public int del_Logistics(String id)
    {
        return dao.del_Logistics(id);
    }

}
