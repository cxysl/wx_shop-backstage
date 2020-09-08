package com.cxysl.dao;

import com.cxysl.comm.Page;
import com.cxysl.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersDAOImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 表中总记录数
     * @return
     */

    public Integer queryCount(){
        String sql01 = "select count(*) from orders";
        return jdbcTemplate.queryForObject(sql01,Integer.class);
    }

    /**
     * 获取查询分页结果集
     * @param page
     * @return
     */
    public Page<Orders> queryAll(Page<Orders> page){
        page.setTotalCount(queryCount());

        String sql = "select * from orders limit ?,?";
        RowMapper<Orders> rowMapper = new BeanPropertyRowMapper<>(Orders.class);
        List<Orders> list = jdbcTemplate.query(sql,rowMapper,(page.getCurenPage()-1)*page.getPageSize(),page.getPageSize());
        page.setArrys(list);
        return page;
    }

    /**
     * 查找最后一个对象
     * @return
     */
    public Orders getLastOne(){
        String sql = "select * from orders order by orderId Desc limit 1;";
        RowMapper<Orders> rowMapper = new BeanPropertyRowMapper<>(Orders.class);
        return jdbcTemplate.queryForObject(sql,rowMapper);
    }


    /**
     * 根据ID查找单个对象
     * @param
     * @return
     */
    public Orders getOrderOne(Integer orderId)
    {
        String sql = "select * from orders where orderId=?";
        RowMapper<Orders> rowMapper = new BeanPropertyRowMapper<>(Orders.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,orderId);
    }

//    /**
//     * 根据ID查询顾客信息
//     * 收货地址
//     * @param cId
//     * @return
//     */
//    public Customer getCustomer(Integer cId)
//    {
//        String sql = "select * from customer c where c.customerId=1;";
//        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
//        return jdbcTemplate.queryForObject(sql,rowMapper,cId);
//    }

    /**
     * 添加新订单信息
     * @param p
     * @return
     */
    public int add_Order(Orders p)
    {
        String sql = "insert into orders values(null,?,?,?,?,?)";
        return jdbcTemplate.update(sql,p.getCustomerName(),p.getCustomerId(),p.getOrderPrice(),p.getOrderDate(),p.getOrderState());
    }

    /**
     *修改订单整体信息
     * @param p
     * @return
     */
    public int Update_Order(Orders p)
    {
        String sql = "update orders set customerName=?,customerId=?,orderPrice=?,orderDate=?,orderState=? where orderId=?";
        return jdbcTemplate.update(sql,p.getCustomerName(),p.getCustomerId(),p.getOrderPrice()
                                        ,p.getOrderDate(),p.getOrderState(),p.getOrderId());
    }

    /**
     * 修改物流状态信息
     * @param state
     * @param p
     * @return
     */
    public int Update_OrderState(String state, Orders p)
    {
        String sql = "update orders set orderState=? where orderId=?";
        return jdbcTemplate.update(sql,state,p.getOrderId());
    }

    /**
     * 计算订单id为id的订单消费总金额
     * @param id
     * @return
     */
    public int Update_OrderSumPrice(Integer id)
    {
        String sql = "update orders o set orderPrice=(select sum(od.goodsPrice*od.buyCount) from orderDetails od where od.orderId=?) where o.orderId=?;";
        return jdbcTemplate.update(sql,id,id);
    }

    /**
     * 删除这个订单信息
     * @param id
     * @return
     */
    public int del_Order(Integer id)
    {
        String sql = "delete from orders where orderId=?";
        return jdbcTemplate.update(sql,id);
    }

}
