package com.cxysl.controller;

import com.cxysl.comm.Page;
import com.cxysl.entity.GoodsType;
import com.cxysl.service.GoodsTypeServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class GoodsTypeServlet {

    @Autowired
    private GoodsTypeServie service;

    /**
     * 分页查
     * @param pageid
     * @return
     */
    @RequestMapping("/queryTypeByPage/{page}")
    public ModelAndView show(@PathVariable("page") Integer pageid){
        Page<GoodsType> page = new Page<>();
        ModelAndView modelAndView = new ModelAndView();
        if(null!=pageid||pageid.intValue()!=0)
        {
            page.setCurenPage(pageid);
        }
        page = service.queryAll(page);
        modelAndView.addObject("page",page);

        modelAndView.setViewName("table/GoodsType");
        return modelAndView;
    }

    /**
     * 增加
     * @param req
     * @param resp
     * @param type 对象
     * @param uname 对象参数
     * @param pwd 对象参数
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/addType.do")
    public void add_Type(HttpServletRequest req, HttpServletResponse resp, GoodsType type, String uname, String pwd)throws ServletException, IOException {
        System.out.println("添加商品类别");
        req.setCharacterEncoding("UTF-8");
        int flag = service.add_Type(type);
        req.getRequestDispatcher("/queryTypeByPage/1").forward(req,resp);
    }

    /**
     *删除
     * @param req
     * @param typeId
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/delType.do")
    public void del_orders(HttpServletRequest req, int typeId,HttpServletResponse resp)throws ServletException, IOException{
        System.out.println("删除是商品类别");
        req.setCharacterEncoding("UTF-8");
        service.del_Type(typeId);
        req.getRequestDispatcher("/queryTypeByPage/1").forward(req,resp);
    }

    /**
     * 修改
     * @param req
     * @param resp
     * @param count
     * @param type
     * @param typeId1
     * @param uname
     * @param pwd
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/updateType.do")
    public void update_Type(HttpServletRequest req, HttpServletResponse resp,String count,GoodsType type,String typeId1,String uname,String pwd)throws ServletException, IOException {
        System.out.println("\n修改商品类别信息"+count);
        req.setCharacterEncoding("UTF-8");

        if (count==null) {
            int typeId = Integer.parseInt(typeId1);
            GoodsType type1 = service.getGoodsTypeOne(typeId); //查找该该id号的type对象
            req.setAttribute("type", type1);

            System.out.println("先找到改对象\t商品类别ID:"+type1.getTypeId()+"\t商品类别名称:"+type1.getTypeName());

            req.getRequestDispatcher("/view/form-basic/GoodsType.jsp").forward(req, resp);   //请求转发到表单页面
        }

        if(count!=null&&count.equals("2")) {
            System.out.println("第二次进来，直接修改信息");
            //说明进行的是修改操作,第二次进来，直接修改
            int flag = service.Update_Type(type);
            req.getRequestDispatcher("/queryTypeByPage/1").forward(req,resp);
        }

    }
}
