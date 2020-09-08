package com.cxysl.service;

import com.cxysl.comm.Page;
import com.cxysl.dao.GoodsTypeDAOImpl;
import com.cxysl.entity.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsTypeServie {
    @Autowired
    private GoodsTypeDAOImpl dao;

    public Page<GoodsType> queryAll(Page<GoodsType> page){
        return  dao.queryAll(page);
    }

    public GoodsType getGoodsTypeOne(Integer id){
        return dao.getGoodsTypeOne(id);
    }

    public int add_Type(GoodsType p){

        return dao.add_Type(p);
    }

    public int Update_Type(GoodsType p){

        return dao.Update_Type(p);
    }

    public int del_Type(Integer id)
    {
        return dao.del_Type(id);
    }
}
