package com.cxysl.controller;

import com.cxysl.comm.Page;
import com.cxysl.entity.Customer;
import com.cxysl.entity.Orders;
import com.cxysl.service.CustomerService;
import com.cxysl.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class OrdersServlet {
    @Autowired
    private OrdersService service;
    @Autowired
    private CustomerService service1;

    /**
     * 分页查
     * @param pageid
     * @return
     */
    @RequestMapping("/queryOrderByPage/{page}")
    public ModelAndView getOrderByPage(@PathVariable("page") Integer pageid){
        Page<Orders> page = new Page<>();
        ModelAndView modelAndView = new ModelAndView();
        if(null!=pageid||pageid.intValue()!=0)
        {
            page.setCurenPage(pageid);
        }
        page = service.queryAll(page);
        modelAndView.addObject("page",page);

        modelAndView.setViewName("/table/Orders_t");
        return modelAndView;
    }

    /**
     * 查顾客信息
     * @return
     */
    @RequestMapping("/queryCustomerByPage/0")
    public ModelAndView getCustomerByPage(Integer customerId,Customer customer ){

        ModelAndView modelAndView = new ModelAndView();

        customer= service1.getCustomerOne(customerId);
        modelAndView.addObject("customer",customer);

        modelAndView.setViewName("/Sundry_bag/Customer_OrderTable");
        return modelAndView;
    }

    /**
     * 修改订单状态
     * @param req
     * @param resp
     * @param orderId1
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping("/updateOrderState.do")
    public void Update_GoodsUpdates(HttpServletRequest req,HttpServletResponse resp,String orderId1) throws IOException, ServletException {
        String new1 = null;
        Orders orders = service.getOrderOne(Integer.valueOf(orderId1));
        String old = orders.getOrderState();       //订单原始值
        if(old.equals("待付款")){
            new1 = "已支付";
        }
        else if(old.equals("已支付")){
            new1 = "已支付";
        }
        else{
            new1 = "待付款";
        }
        int flag = service.Update_OrderState(new1,orders);
            System.out.println("订单表中状态修改成功12138");
            req.getRequestDispatcher("/queryOrderByPage/1").forward(req,resp);
    }


    /**
     * 删除
     * @param req
     * @param orderId
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/delOrder.do")
    public void del_Goods(HttpServletRequest req, int orderId, HttpServletResponse resp)throws ServletException, IOException{
        System.out.println("删除订单");
        req.setCharacterEncoding("UTF-8");
        service.del_Order(orderId);
        req.getRequestDispatcher("/queryOrderByPage/1").forward(req,resp);
    }

    /**
     * 添加订单
     *  Success
     *  addOrder.do?customerName=董思危&customerId=11&orderPrice=200&orderState=待付款
     * @param req
     * @param resp
     * @param order
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/addOrder.do")
    public void add_Logistics(HttpServletRequest req, HttpServletResponse resp, Orders order)throws ServletException, IOException {

        System.out.println("添加订单");
        req.setCharacterEncoding("UTF-8");
        SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy-MM-dd  HH:mm");
        String OrderDate =   dateFormat.format( new Date() );        //下单时间
        order.setOrderDate(OrderDate);

//        order.setOrderPrice(service.Update_OrderSumPrice(order.getOrderId()));
        int flag = service.add_Order(order);      /**添加订单     **/
        req.getRequestDispatcher("/queryOrderByPage/1").forward(req,resp);
    }




}
//http://www.jq22.com/jquery-info18897
////        http://www.jq22.com/jquery-info20622