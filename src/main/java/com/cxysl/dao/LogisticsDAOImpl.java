package com.cxysl.dao;

import com.cxysl.comm.Page;
import com.cxysl.entity.Logistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogisticsDAOImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 过得总记录数
     * @return
     */

    public Integer queryCount(){
        String sql01 = "select count(*) from logistics";
        return jdbcTemplate.queryForObject(sql01,Integer.class);
    }

    /**
     * 获取查询分页结果集
     * @param page
     * @return
     */
    public Page<Logistics> queryAll(Page<Logistics> page){
        page.setTotalCount(queryCount());

        String sql = "select * from logistics limit ?,?";
        RowMapper<Logistics> rowMapper = new BeanPropertyRowMapper<>(Logistics.class);
        List<Logistics> list = jdbcTemplate.query(sql,rowMapper,(page.getCurenPage()-1)*page.getPageSize(),page.getPageSize());
        page.setArrys(list);
        return page;
    }


    /**
     * 根据快递单号查找单个物流信息
     * @param expressId
     * @return
     */
    public Logistics getLogisticsOne(String expressId)
    {
        String sql = "select * from logistics where expressId=?";
        RowMapper<Logistics> rowMapper = new BeanPropertyRowMapper<>(Logistics.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,expressId);
    }

    /**
     * 根据订单单号查找单个物流信息
     * @param orderId
     * @return
     */
    public Logistics getLogisticsOne2(int orderId)
    {
        String sql = "select * from logistics where expressId=?";
        RowMapper<Logistics> rowMapper = new BeanPropertyRowMapper<>(Logistics.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,orderId);
    }


    /**
     * 添加新物流信息
     * @param p
     * @return
     */
    public int add_Logistics(Logistics p)
    {
        String sql = "insert into logistics values(?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,p.getExpressId(),p.getOrderId(),p.getCustomerId(),p.getCustomerName(),
                                        p.getCustomerAddress(),p.getCustomerPhone(),p.getLogisticsState(),p.getLogisticsAddress());
    }

    /**
     * 修改物流状态信息
     * @param state
     * @param p
     * @return
     */
    public int Update_LogisticsState(String state, Logistics p)
    {
        String sql = "update logistics set logisticsState=? where expressId=?";
        return jdbcTemplate.update(sql,state,p.getExpressId());
    }

    /**
     * 修改物流地址信息信息
     * @param Address
     * @param p
     * @return
     */
    public int Update_LogisticsNowAddress(String Address,Logistics p)
    {
        String sql = "update logistics set logisticsAddress=? where expressId=?";
        return jdbcTemplate.update(sql,Address,p.getExpressId());
    }

    /**
     * 删除物流信息
     * @param id
     * @return
     */
    public int del_Logistics(String id)
    {
        String sql = "delete from logistics where expressId=?";
        return jdbcTemplate.update(sql,id);
    }


}
