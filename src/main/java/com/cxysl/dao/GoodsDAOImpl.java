package com.cxysl.dao;

import com.cxysl.comm.Page;
import com.cxysl.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodsDAOImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 过得总记录数
     * @return
     */

    public Integer queryCount(){
        String sql01 = "select count(*) from goods";
        return jdbcTemplate.queryForObject(sql01,Integer.class);
    }

    /**
     * 获取查询分页结果集
     * @param page
     * @return
     */
    public Page<Goods> queryAll(Page<Goods> page){
        page.setTotalCount(queryCount());

        String sql = "select * from goods limit ?,?";
        RowMapper<Goods> rowMapper = new BeanPropertyRowMapper<>(Goods.class);
        List<Goods> list = jdbcTemplate.query(sql,rowMapper,(page.getCurenPage()-1)*page.getPageSize(),page.getPageSize());
        page.setArrys(list);
        return page;
    }

    /**
     * 获取上架商品查询分页结果集
     * @param page
     * @return
     */
    public Page<Goods> queryUpAll(Page<Goods> page){
        page.setTotalCount(queryCount());

        String sql = "select * from goods where goodsUpdates='y' limit ?,? ";
        RowMapper<Goods> rowMapper = new BeanPropertyRowMapper<>(Goods.class);
        List<Goods> list = jdbcTemplate.query(sql,rowMapper,(page.getCurenPage()-1)*page.getPageSize(),page.getPageSize());
        page.setArrys(list);
        return page;
    }

    /**
     * 根据ID查找单个商品
     * @param goodsId
     * @return
     */
    public Goods getGoodsOne(Integer goodsId)
    {
        String sql = "select * from goods where goodsId=?";
        RowMapper<Goods> rowMapper = new BeanPropertyRowMapper<>(Goods.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,goodsId);
    }


    /**
     * 添加新商品信息
     * @param p
     * @return
     */
    public int add_product(Goods p)
    {
        String sql = "insert into goods values(null,?,?,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,p.getGoodsName(),p.getGoodsPicture(),p.getPurchasePrice(),p.getGoodsPrice(),
                                        p.getGoodsCount(),p.getGoodsDescribe(),p.getGoodLabel1(),
                                        p.getGoodLabel2(),p.getGoodLabel3(),p.getGoodsUpdates(),p.getTypeId());
    }

    /**
     *修改商品整体信息
     * @param p
     * @return
     */
    public int Update_product(Goods p)
    {
        String sql = "update goods set goodsName=?,goodsPicture=?,purchasePrice=?,goodsPrice=?,goodsCount=?" +
                ",goodsDescribe=?,goodLabel1=?,goodLabel2=?,goodLabel3=?,goodsUpdates=?,typeId=? where goodsId=?";
        return jdbcTemplate.update(sql,p.getGoodsName(),p.getGoodsPicture(),p.getPurchasePrice(),p.getGoodsPrice(),
                                        p.getGoodsCount(),p.getGoodsDescribe(),p.getGoodLabel1(),
                                        p.getGoodLabel2(),p.getGoodLabel3(),p.getGoodsUpdates(),
                                        p.getTypeId(),p.getGoodsId());
    }

    /**
     * 删除这个商品信息
     * @param id
     * @return
     */
    public int del_product(Integer id)
    {
        String sql = "delete from goods where goodsId=?";
        return jdbcTemplate.update(sql,id);
    }

    /**
     * 改指定商品的库存量
     * @param g
     * @param a
     * @return
     */
    public int Update_GoodsCount(Goods g,Integer a)
    {
        String sql = "update goods set goodsCount=? where goodsId=?";
        return jdbcTemplate.update(sql,a,g.getGoodsId());
    }

    /**
     * 改商品上下架状态
     * @param g
     * @param goodsUpdates
     * @return
     */
    public int Update_GoodsUpdates(Goods g,String goodsUpdates)
    {
        String sql = "update goods set goodsUpdates=? where goodsId=?";
        return jdbcTemplate.update(sql,goodsUpdates,g.getGoodsId());
    }
}
