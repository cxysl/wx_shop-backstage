package com.cxysl.service;

import com.cxysl.comm.Page;
import com.cxysl.dao.GoodsPictureDAOImpl;
import com.cxysl.entity.GoodsPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsPictureSrevie {
    @Autowired
    private GoodsPictureDAOImpl dao;

    public Page<GoodsPicture> queryAll(Page<GoodsPicture> page){
        return  dao.queryAll(page);
    }

    public GoodsPicture getGoodsPictureOne(Integer id){
        return dao.getGoodsPictureOne(id);
    }

    public int add_Picture(GoodsPicture p){

        return dao.add_Picture(p);
    }

    public int Update_Picture(GoodsPicture p){

        return dao.Update_Picture(p);
    }

    public int del_Picture(Integer id)
    {
        return dao.del_Picture(id);
    }
}
