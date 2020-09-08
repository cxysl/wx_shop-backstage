package com.cxysl.dao;

import com.cxysl.comm.Page;
import com.cxysl.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginDAOImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 登录验证
     * @return
     */
    public UserInfo Login(String uname, String pwd) {
        String sql = "select * from t_user where uname=? and pwd=?";
        RowMapper<UserInfo> rowMapper = new BeanPropertyRowMapper<>(UserInfo.class);
        UserInfo u =null;
        try {
            u = jdbcTemplate.queryForObject(sql,rowMapper,uname,pwd);
        }catch (EmptyResultDataAccessException e){
            System.out.println("111");
            return  null;

        }
        if (null==u){
            System.out.println("1111");
        }
            return u;
    }


    /**
     * 过得总记录数
     * @return
     */

    public Integer queryCount(){
        String sql01 = "select count(*) from t_user";
        return jdbcTemplate.queryForObject(sql01,Integer.class);
    }

    /**
     * 获取查询分页结果集
     * @param page
     * @return
     */
    public Page<UserInfo> queryAll(Page<UserInfo> page){
        page.setTotalCount(queryCount());

        String sql = "select * from t_user limit ?,?";
        RowMapper<UserInfo> rowMapper = new BeanPropertyRowMapper<>(UserInfo.class);
        List<UserInfo> list = jdbcTemplate.query(sql,rowMapper,(page.getCurenPage()-1)*page.getPageSize(),page.getPageSize());
        page.setArrys(list);
        return page;
    }

    /**
     * 根据ID查询单个
     * @param pid
     * @return
     */
    public UserInfo getUserInfoOne(Integer pid)
    {
        String sql = "select * from t_user where pid=?";
        RowMapper<UserInfo> rowMapper = new BeanPropertyRowMapper<>(UserInfo.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,pid);
    }

    /**
     * 增
     * @param p
     * @return
     */
    public int add_user(UserInfo p)
    {
        String sql = "insert into t_user values(null,?,?)";
        return jdbcTemplate.update(sql,p.getUname(),p.getPwd());
    }

    /**
     * 修改
     * @param p
     * @return
     */
    public int Update_user(UserInfo p)
    {
        String sql = "update t_user set uname=?,pwd=? where pid=?";
        return jdbcTemplate.update(sql,p.getUname(),p.getPwd(),p.getPid());
    }

    /**
     * 查
     * @param id
     * @return
     */
    public int del_user(Integer id)
    {
        String sql = "delete from t_user where pid=?";
        return jdbcTemplate.update(sql,id);
    }


}
