package com.cxysl.controller;

import com.cxysl.comm.Page;
import com.cxysl.entity.Logistics;
import com.cxysl.service.LogisticsService;
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
public class LogisticsServlet {

    @Autowired
    private LogisticsService service;

    /**
     * 分页查
     * @param pageid
     * @return
     */
    @RequestMapping("/queryLogisticsByPage/{page}")
    public ModelAndView getProductByPage(@PathVariable("page") Integer pageid){
        Page<Logistics> page = new Page<>();
        ModelAndView modelAndView = new ModelAndView();
        if(null!=pageid||pageid.intValue()!=0)
        {
            page.setCurenPage(pageid);
        }
        page = service.queryAll(page);
        modelAndView.addObject("page",page);

        modelAndView.setViewName("/table/Logistics_t");
        return modelAndView;
    }

    /**
     * 根据订单Id查看物流信息
     * @return
     */
    @RequestMapping("/queryLogisticsByPage/0")
    public ModelAndView getLogisticsOne2(HttpServletRequest req,int orderId,Logistics log)throws NumberFormatException{

        System.out.println("进来成功!");
        ModelAndView modelAndView = new ModelAndView();
//
//        if (req.getSession().getAttribute("orderId3")!=null){
//            Integer orderId3 = Integer.parseInt(String.valueOf(req.getSession().getAttribute("orderId3")));
//            System.out.println("查看物流信息"+orderId3);
//            log = service.getLogisticsOne2(orderId3);
//            modelAndView.addObject("log",log);
//
//            System.out.println(log.getLogisticsState()+"\t查看指定订单修改物流状态后物流信息\t"+log.getCustomerPhone());
//
//            modelAndView.setViewName("/Sundry_bag/OrderLogisticsTable");
//            return modelAndView;
//        }
//        else{
            log = service.getLogisticsOne2(orderId);
            System.out.println("orderId"+orderId+"\t\t"+log.getCustomerAddress());
            modelAndView.addObject("log",log);

            System.out.println(log.getLogisticsState()+"\t查看指定订单物流信息\t"+log.getCustomerPhone());

            modelAndView.setViewName("/Sundry_bag/OrderLogisticsTable");
            return modelAndView;
//        }
    }

    /**
     * 删除
     * @param req
     * @param expressId
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/delLogistics.do")
    public void del_Goods(HttpServletRequest req, String expressId,HttpServletResponse resp)throws ServletException, IOException{
        System.out.println("删除商品");
        req.setCharacterEncoding("UTF-8");
        service.del_Logistics(expressId);
        req.getRequestDispatcher("/queryLogisticsByPage/1").forward(req,resp);
    }

    /**
     * 修改订单物流状态
     * @param req
     * @param resp
     * @param expressId1
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping("/updateLogisticsState.do")
    public void Update_GoodsUpdates(HttpServletRequest req,HttpServletResponse resp,String expressId1,String orderId,String orderId2,String type) throws IOException, ServletException {
        String new1 = null;
        System.out.println("expressId1:"+expressId1+"\torderId:"+orderId+"\torderId2:"+orderId2+"\ttype:"+type);
        Logistics logistics = service.getLogisticsOne(expressId1);//找到该对象

        String old = logistics.getLogisticsState();       //上下架状态原始值

        if(old.equals("待发货")){
            new1 = "待收货";
        }
        else if(old.equals("待收货")){
            new1 = "已收货";
        }
        else if(old.equals("已收货")){
            new1 = "已收货";
        }
        else{
            new1 = "待发货";
        }
        int flag = service.Update_LogisticsState(new1,logistics);
        if(type!=null&&type.equals("2")){

//            req.getSession().setAttribute("orderId3",orderId);
//            System.out.println("单个物流信息修改\t"+orderId+"12138\t取orderId3:"+req.getSession().getAttribute("orderId3"));

            this.getLogisticsOne2(req, Integer.parseInt(orderId2),logistics);
//            req.getRequestDispatcher("/queryLogisticsByPage/0}").forward(req,resp);
        }else{

            System.out.println("物流表中状态修改成功12138");
            req.getRequestDispatcher("/queryLogisticsByPage/1").forward(req,resp);
        }

    }

    /**
     * 更新物流信息
     * @param req
     * @param resp
     * @param logisticsAddress
     * @param countgoods
     * @param log
     * @param expressId1
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping("/updateLogisticsNowAddress.do")
    public void Update_LogisticsNowAddress(HttpServletRequest req,HttpServletResponse resp,String logisticsAddress,String countgoods,Logistics log,String expressId1) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        System.out.println("\n修改物流信息"+countgoods);

        if (countgoods==null){

            Logistics log1 = service.getLogisticsOne(expressId1);//查找该该id号的Goods1对象

            req.setAttribute("log", log1);

            System.out.println(log1.getLogisticsAddress());

            req.getRequestDispatcher("/view/form-basic/Logistics_f.jsp").forward(req, resp);   //请求转发到表单页面
        }

        if(countgoods!=null&&countgoods.equals("2")) {
            System.out.println("第二次进来，直接修改信息");
            //说明进行的是修改操作,第二次进来，直接修改

            int flag = service.Update_LogisticsNowAddress(logisticsAddress,log);
            req.getRequestDispatcher("/queryLogisticsByPage/1").forward(req,resp);
        }
    }


    /**
     * 增加
     * @param req
     * @param resp
     * @param logistics
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/addlogistics.do")
    public void add_Logistics(HttpServletRequest req, HttpServletResponse resp, Logistics logistics)throws ServletException, IOException {

        System.out.println("添加物流");
        req.setCharacterEncoding("UTF-8");

        int flag = service.add_Logistics(logistics);      /**增加商品     **/
        req.getRequestDispatcher("/queryLogisticsByPage/1").forward(req,resp);
    }
/**
 *  Success
 * addlogistics.do?expressId=007&orderId=7&customerId=20&customerName=阿萨德&customerAddress=地球&customerPhone=10011&logisticsState=待发货&logisticsAddress=武汉
 */

    }