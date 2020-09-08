package com.cxysl.service;

import com.cxysl.comm.Page;
import com.cxysl.dao.GoodsPurchaseDAOImpl;
import com.cxysl.entity.GoodsPurchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsPurchaseSrevie {

    @Autowired
    private GoodsPurchaseDAOImpl dao;

    public Page<GoodsPurchase> queryAll(Page<GoodsPurchase> page){
        return  dao.queryAll(page);
    }

    public int add_Purchase(GoodsPurchase p){

        return dao.add_Purchase(p);
    }
}
