package com.cxysl.dao;

import com.cxysl.comm.Page;
import com.cxysl.entity.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class GoodsTypeDAOImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 过得总记录数
     * @return
     */

    public Integer queryCount(){
        String sql01 = "select count(*) from goodsType";
        return jdbcTemplate.queryForObject(sql01,Integer.class);
    }

    /**
     * 获取查询分页结果集
     * @param page
     * @return
     */
    public Page<GoodsType> queryAll(Page<GoodsType> page){
        page.setTotalCount(queryCount());

        String sql = "select * from goodsType limit ?,?";
        RowMapper<GoodsType> rowMapper = new BeanPropertyRowMapper<>(GoodsType.class);
        List<GoodsType> list = jdbcTemplate.query(sql,rowMapper,(page.getCurenPage()-1)*page.getPageSize(),page.getPageSize());
        page.setArrys(list);
        return page;
    }

    /**
     * 根据ID查询单个
     * @param typeId
     * @return
     */
    public GoodsType getGoodsTypeOne(Integer typeId)
    {
        String sql = "select * from goodsType where typeId=?";
        RowMapper<GoodsType> rowMapper = new BeanPropertyRowMapper<>(GoodsType.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,typeId);
    }

    /**
     * 增
     * @param p
     * @return
     */
    public int add_Type(GoodsType p)
    {
        String sql = "insert into goodsType values(null,?)";
        return jdbcTemplate.update(sql,p.getTypeName());
    }

    /**
     * 修改
     * @param p
     * @return
     */
    public int Update_Type(GoodsType p)
    {
        String sql = "update goodsType set typeName=? where typeId=?";
        return jdbcTemplate.update(sql,p.getTypeName(),p.getTypeId());
    }

    /**
     * 查
     * @param id
     * @return
     */
    public int del_Type(Integer id)
    {
        String sql = "delete from goodsType where typeId=?";
        return jdbcTemplate.update(sql,id);
    }
}
