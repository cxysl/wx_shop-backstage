package com.cxysl.dao;

import com.cxysl.comm.Page;
import com.cxysl.entity.GoodsPurchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodsPurchaseDAOImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 过得总记录数
     * @return
     */

    public Integer queryCount(){
        String sql01 = "select count(*) from goodsPurchase";
        return jdbcTemplate.queryForObject(sql01,Integer.class);
    }

    /**
     * 获取查询分页结果集
     * @param page
     * @return
     */
    public Page<GoodsPurchase> queryAll(Page<GoodsPurchase> page){
        page.setTotalCount(queryCount());

        String sql = "select * from goodsPurchase  limit ?,?";
        RowMapper<GoodsPurchase> rowMapper = new BeanPropertyRowMapper<>(GoodsPurchase.class);
        List<GoodsPurchase> list = jdbcTemplate.query(sql,rowMapper,(page.getCurenPage()-1)*page.getPageSize(),page.getPageSize());
        page.setArrys(list);
        return page;
    }


    /**
     * 添加新商品信息
     * @param p
     * @return
     */
    public int add_Purchase(GoodsPurchase p)
    {
        String sql = "insert into goodsPurchase values(null,?,?,?,?,?)";
        return jdbcTemplate.update(sql,p.getPurchaseCount(),p.getPurchasePrice(),p.getPurchaseDate(),p.getGoodsName(),p.getGoodsId());
    }
}
