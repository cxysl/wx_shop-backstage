package com.cxysl.service;

import com.cxysl.comm.Page;
import com.cxysl.dao.GoodsDAOImpl;
import com.cxysl.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServie {
    @Autowired
    private GoodsDAOImpl dao;

    public Page<Goods> queryAll(Page<Goods> page){
        return  dao.queryAll(page);
    }

    public Page<Goods> queryUpAll(Page<Goods> page){
        return  dao.queryUpAll(page);
    }

    public Goods getGoodsOne(Integer id){
        return dao.getGoodsOne(id);
    }

    public int add_product(Goods p){

        return dao.add_product(p);
    }

    public int Update_product(Goods p){

        return dao.Update_product(p);
    }

    public int del_product(Integer id)
    {
        return dao.del_product(id);
    }

    /**
     * 改指定商品的库存量
     * @param g
     * @param count
     * @return
     */
    public int Update_GoodsCount(Goods g,Integer count){
        return dao.Update_GoodsCount(g,count);
    }

    /**
     * 改商品上下架状态
     * @param g
     * @param goodsUpdates
     * @return
     */
    public int Update_GoodsUpdates(Goods g,String goodsUpdates){
        return dao.Update_GoodsUpdates(g,goodsUpdates);
    }
}
