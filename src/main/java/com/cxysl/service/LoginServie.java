package com.cxysl.service;

import com.cxysl.comm.Page;
import com.cxysl.dao.LoginDAOImpl;
import com.cxysl.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServie {

    @Autowired
    private LoginDAOImpl dao;

    public UserInfo Login(String uname, String pwd){
        return dao.Login(uname,pwd);
    }

    public Page<UserInfo> queryAll(Page<UserInfo> page){
        return  dao.queryAll(page);
    }

    public UserInfo getUserInfoOne(Integer id){
        return dao.getUserInfoOne(id);
    }

    public int add_user(UserInfo p){

        return dao.add_user(p);
    }

    public int Update_user(UserInfo p){

        return dao.Update_user(p);
    }

    public int del_user(Integer id)
    {
        return dao.del_user(id);
    }
}
