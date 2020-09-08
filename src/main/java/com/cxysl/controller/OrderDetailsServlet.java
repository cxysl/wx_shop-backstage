package com.cxysl.controller;

import com.cxysl.comm.Page;
import com.cxysl.entity.OrderDetails;
import com.cxysl.service.OrderDetailsService;
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
public class OrderDetailsServlet {
    @Autowired
    private OrderDetailsService service;

    /**
     * 分页查
     * @param pageid
     * @return
     */
    @RequestMapping("/queryODByPage/{page}")
    public ModelAndView getProductByPage(@PathVariable("page") Integer pageid){
        Page<OrderDetails> page = new Page<>();
        ModelAndView modelAndView = new ModelAndView();
        if(null!=pageid||pageid.intValue()!=0)
        {
            page.setCurenPage(pageid);
        }
        page = service.queryAll(page);
        modelAndView.addObject("page",page);
        System.out.println("连接成功！");
        modelAndView.setViewName("/table/OrderDetails_t");
        return modelAndView;
    }

    /**
     * 根据订单ID查一个订单
     * @param pageid
     * @param orderId
     * @return
     */
    @RequestMapping("/queryODOneByPage/{page}")
    public ModelAndView getOrderOne(@PathVariable("page") Integer pageid,int orderId){
        Page<OrderDetails> page = new Page<>();
        ModelAndView modelAndView = new ModelAndView();
        if(null!=pageid||pageid.intValue()!=0)
        {
            page.setCurenPage(pageid);
        }
        page = service.getOrderOne(page,orderId);
        modelAndView.addObject("page",page);

        modelAndView.setViewName("/table/OrderDetails_t");
        return modelAndView;
    }



    /**
     * 删除
     * @param req
     * @param goodsId1
     * @param orderId1
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/delOrderDetails.do")
    public void del_Goods(HttpServletRequest req, int goodsId1,int orderId1, HttpServletResponse resp)throws ServletException, IOException{
        System.out.println("删除一条订单明细");
        req.setCharacterEncoding("UTF-8");
        service.del_OrderDetails(goodsId1,orderId1);
        req.getRequestDispatcher("/queryODByPage/1").forward(req,resp);
    }

    /**
     * 添加订单
     *  Success
     *  addOrderDetails.do?goodsName=衬衣&buyCount=2&goodsPrice=120&orderId=12
     * @param req
     * @param resp
     * @param od
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/addOrderDetails.do")
    public void add_Logistics(HttpServletRequest req, HttpServletResponse resp, OrderDetails od)throws ServletException, IOException {

        System.out.println("添加订单详情");
        req.setCharacterEncoding("UTF-8");


        int flag = service.add_OrderDetails(od);      /**添加订单详情     **/
        req.getRequestDispatcher("/queryODByPage/1").forward(req,resp);
    }

}
