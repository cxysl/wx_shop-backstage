package com.cxysl.service;

import com.cxysl.comm.Page;
import com.cxysl.dao.CustomerDAOImpl;
import com.cxysl.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAOImpl dao;

    public Page<Customer> queryAll(Page<Customer> page){
        return  dao.queryAll(page);
    }


    public Customer getCustomerOne(Integer id){
        return dao.getCustomerOne(id);
    }

    public int add_Customer(Customer p){

        return dao.add_Customer(p);
    }

    public int Update_Customer(Customer p){

        return dao.Update_Customer(p);
    }

    public int del_Customer(Integer id)
    {
        return dao.del_Customer(id);
    }
}
