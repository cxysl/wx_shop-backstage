package com.cxysl.dao;

import com.cxysl.comm.Page;
import com.cxysl.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 过得总记录数
     * @return
     */

    public Integer queryCount(){
        String sql01 = "select count(*) from customer";
        return jdbcTemplate.queryForObject(sql01,Integer.class);
    }

    /**
     * 获取查询分页结果集
     * @param page
     * @return
     */
    public Page<Customer> queryAll(Page<Customer> page){
        page.setTotalCount(queryCount());

        String sql = "select * from customer limit ?,?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        List<Customer> list = jdbcTemplate.query(sql,rowMapper,(page.getCurenPage()-1)*page.getPageSize(),page.getPageSize());
        page.setArrys(list);
        return page;
    }

    /**
     * 根据ID查找单个商品
     * @param customerId
     * @return
     */
    public Customer getCustomerOne(Integer customerId)
    {
        String sql = "select * from customer where customerId=?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,customerId);
    }

    /**
     * 添加新商品信息
     * @param p
     * @return
     */
    public int add_Customer(Customer p)
    {
        String sql = "insert into customer values(null,?,?,?,?,?)";
        return jdbcTemplate.update(sql,p.getCustomerName(),p.getCustomerSex(),
                p.getCustomerPhone(),p.getCustomerAddress(),p.getCustomerPicture());
    }

    /**
     *修改商品整体信息
     * @param p
     * @return
     */
    public int Update_Customer(Customer p)
    {
        String sql = "update customer set customerName=?,customerSex=?,customerPhone=?," +
                "customerAddress=?,customerPicture=? where customerId=?";
        return jdbcTemplate.update(sql,p.getCustomerName(),p.getCustomerSex(),
                                        p.getCustomerPhone(),p.getCustomerAddress(),
                p.getCustomerPicture(),p.getCustomerId());
    }

    /**
     * 删除这个商品信息
     * @param id
     * @return
     */
    public int del_Customer(Integer id)
    {
        String sql = "delete from customer where customerId=?";
        return jdbcTemplate.update(sql,id);
    }
}
