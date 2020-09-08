package com.cxysl.dao;

import com.cxysl.comm.Page;
import com.cxysl.entity.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDetailsDAOImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 过得总记录数
     * @return
     */

    public Integer queryCount(){
        String sql01 = "select count(*) from orderDetails";
        return jdbcTemplate.queryForObject(sql01,Integer.class);
    }

    /**
     * 获取查询分页结果集
     * @param page
     * @return
     */
    public Page<OrderDetails> queryAll(Page<OrderDetails> page){
        page.setTotalCount(queryCount());

        String sql = "select * from orderDetails limit ?,?";
        RowMapper<OrderDetails> rowMapper = new BeanPropertyRowMapper<>(OrderDetails.class);
        List<OrderDetails> list = jdbcTemplate.query(sql,rowMapper,(page.getCurenPage()-1)*page.getPageSize(),page.getPageSize());
        page.setArrys(list);
        return page;
    }


    /**
     * 根据ID查找单个记录
     * @param goodsId
     * @param orderId
     * @return
     */
    public OrderDetails getOrderDetailsOne(Integer goodsId,Integer orderId)
    {
        String sql = "select * from orderDetails od where od.goodsId=? and od.orderId=?;";
        RowMapper<OrderDetails> rowMapper = new BeanPropertyRowMapper<>(OrderDetails.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,goodsId,orderId);
    }

    /**
     * 根据订单ID查找一个订单
     * @param orderId
     * @return
     */
    public Page<OrderDetails> getOrderOne(Page<OrderDetails> page,Integer orderId)
    {
        page.setTotalCount(queryCount());

        String sql = "select * from orderDetails od where od.orderId=? limit ?,?;";
        RowMapper<OrderDetails> rowMapper = new BeanPropertyRowMapper<>(OrderDetails.class);
        List<OrderDetails> list = jdbcTemplate.query(sql,rowMapper,orderId,(page.getCurenPage()-1)*page.getPageSize(),page.getPageSize());
        page.setArrys(list);
        return page;
    }


    /**
     * 添加一条订单明细信息
     * @param p
     * @return
     */
    public int add_OrderDetails(OrderDetails p)
    {
        String sql = "insert into orderDetails values(?,?,?,?,?)";
        return jdbcTemplate.update(sql,p.getGoodsId(),p.getGoodsName(),p.getBuyCount(),p.getGoodsPrice(),p.getOrderId());
    }

    /**
     *修改一条订单明细信息
     * @param p
     * @return
     */
    public int Update_OrderDetails(OrderDetails p)
    {
        String sql = "update orderDetails set goodsName=?,buyCount=?,goodsPrice=? where orderId=? and goodsId=?";
        return jdbcTemplate.update(sql,p.getGoodsName(),p.getBuyCount(),p.getGoodsPrice(),p.getOrderId(),p.getGoodsId());
    }

    /**
     * 删除一条订单明细信息
     * @param goodsId
     * @param orderId
     * @return
     */
    public int del_OrderDetails(Integer goodsId,Integer orderId)
    {
        String sql = "delete from orderDetails where goodsId=? and orderId=?";
        return jdbcTemplate.update(sql,goodsId,orderId);
    }




}
