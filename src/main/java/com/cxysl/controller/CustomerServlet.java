package com.cxysl.controller;

import com.cxysl.comm.Page;
import com.cxysl.entity.Customer;
import com.cxysl.service.CustomerService;
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
public class CustomerServlet {

    @Autowired
    private CustomerService service;

    /**
     * 分页查
     * @param pageid
     * @return
     */
    @RequestMapping("/queryCustomerByPage/{page}")
    public ModelAndView show(@PathVariable("page") Integer pageid){
        Page<Customer> page = new Page<>();
        ModelAndView modelAndView = new ModelAndView();
        if(null!=pageid||pageid.intValue()!=0)
        {
            page.setCurenPage(pageid);
        }
        page = service.queryAll(page);
        modelAndView.addObject("page",page);

        modelAndView.setViewName("table/Customer");
        return modelAndView;
    }

    /**
     * 增加
     * http://101.200.144.60:9000/images/WX_img/none.jpg
     * @param req
     * @param resp
     * @param customer 对象
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/addCustomer.do")
    public void add_Customer(HttpServletRequest req, HttpServletResponse resp, Customer customer)throws ServletException, IOException {
        System.out.println("添加顾客对象");
        req.setCharacterEncoding("UTF-8");
        int flag = service.add_Customer(customer);
        req.getRequestDispatcher("/queryCustomerByPage/1").forward(req,resp);
    }

    /**
     *删除
     * @param req
     * @param customerId
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/delCustomer.do")
    public void del_orders(HttpServletRequest req, int customerId,HttpServletResponse resp)throws ServletException, IOException{
        System.out.println("删除顾客对象");
        req.setCharacterEncoding("UTF-8");
        service.del_Customer(customerId);
        req.getRequestDispatcher("/queryCustomerByPage/1").forward(req,resp);
    }

    /**
     * 修改
     * @param req
     * @param resp
     * @param count
     * @param customer
     * @param customerId1
     * @param uname
     * @param pwd
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/updateCustomer.do")
    public void update_Customer(HttpServletRequest req, HttpServletResponse resp,String count,Customer customer,String customerId1,String uname,String pwd)throws ServletException, IOException {
        System.out.println("\n修改顾客对象"+count);
        req.setCharacterEncoding("UTF-8");

        if (count==null) {
            int customerId = Integer.parseInt(customerId1);
            Customer customer1 = service.getCustomerOne(customerId); //查找该该id号的customer对象
            req.setAttribute("customer", customer1);

            System.out.println("先找到改对象\t编号ID:"+customer1.getCustomerId());

            req.getRequestDispatcher("/view/form-basic/Customer.jsp").forward(req, resp);   //请求转发到表单页面
        }

        if(count!=null&&count.equals("2")) {
            System.out.println("第二次进来，直接修改商品图片"+customer.getCustomerName()+"\t"+customer.getCustomerSex());
            //说明进行的是修改操作,第二次进来，直接修改

            int flag = service.Update_Customer(customer);
            req.getRequestDispatcher("/queryCustomerByPage/1").forward(req,resp);
        }

    }
}
