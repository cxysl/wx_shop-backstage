package com.cxysl.dao;

import com.cxysl.comm.Page;
import com.cxysl.entity.GoodsPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodsPictureDAOImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 过得总记录数
     * @return
     */

    public Integer queryCount(){
        String sql01 = "select count(*) from goodsPicture";
        return jdbcTemplate.queryForObject(sql01,Integer.class);
    }

    /**
     * 获取查询分页结果集
     * @param page
     * @return
     */
    public Page<GoodsPicture> queryAll(Page<GoodsPicture> page){
        page.setTotalCount(queryCount());

        String sql = "select * from goodsPicture limit ?,?";
        RowMapper<GoodsPicture> rowMapper = new BeanPropertyRowMapper<>(GoodsPicture.class);
        List<GoodsPicture> list = jdbcTemplate.query(sql,rowMapper,(page.getCurenPage()-1)*page.getPageSize(),page.getPageSize());
        page.setArrys(list);
        return page;
    }

    /**
     * 根据ID查询单个
     * @param pictureId
     * @return
     */
    public GoodsPicture getGoodsPictureOne(Integer pictureId)
    {
        String sql = "select * from goodsPicture where pictureId=?";
        RowMapper<GoodsPicture> rowMapper = new BeanPropertyRowMapper<>(GoodsPicture.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,pictureId);
    }

    /**
     * 增
     * @param p
     * @return
     */
    public int add_Picture(GoodsPicture p)
    {
        String sql = "insert into goodsPicture values(null,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,p.getPictureOne(),p.getPictureTwo(),p.getPictureThree(),p.getPictureFour(),p.getPictureFive(),p.getGoodsId());
    }

    /**
     * 修改
     * @param p
     * @return
     */
    public int Update_Picture(GoodsPicture p)
    {
        String sql = "update goodsPicture set pictureOne=?,pictureTwo=?,pictureThree=?,pictureFour=?,pictureFive=?,goodsId=? where pictureId=?";
        return jdbcTemplate.update(sql,p.getPictureOne(),p.getPictureTwo(),p.getPictureThree(),p.getPictureFour(),p.getPictureFive(),
                                        p.getGoodsId(),p.getPictureId());
    }

    /**
     * 查
     * @param id
     * @return
     */
    public int del_Picture(Integer id)
    {
        String sql = "delete from goodsPicture where pictureId=?";
        return jdbcTemplate.update(sql,id);
    }
}
