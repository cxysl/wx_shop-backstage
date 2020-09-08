package com.cxysl.controller;

import com.cxysl.comm.Page;
import com.cxysl.entity.UserInfo;
import com.cxysl.service.LoginServie;
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
public class LoginServlet {

    @Autowired
    private LoginServie service;

    /**
     * 登录验证
     * @param req
     * @param resp
     * @param uname
     * @param pwd
     * @return
     */
    @RequestMapping("/login.do")
    public ModelAndView login(HttpServletRequest req, HttpServletResponse resp, String uname, String pwd){
        ModelAndView modelAndView = new ModelAndView();
        UserInfo userInfo = service.Login(uname,pwd);
        if(userInfo==null) {
            modelAndView.setViewName("login");
        }
        else {
            try {
                req.getRequestDispatcher("/queryOrderByPage/1").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return modelAndView;
    }

    /**
     * 分页查
     * @param pageid
     * @return
     */
    @RequestMapping("/queryUserByPage/{page}")
    public ModelAndView show(@PathVariable("page") Integer pageid){
        Page<UserInfo> page = new Page<>();
        ModelAndView modelAndView = new ModelAndView();
        if(null!=pageid||pageid.intValue()!=0)
        {
            page.setCurenPage(pageid);
        }
        page = service.queryAll(page);
        modelAndView.addObject("page",page);

        modelAndView.setViewName("table/user");
        return modelAndView;
    }

    /**
     * 增加
     * @param req
     * @param resp
     * @param user 对象
     * @param uname 对象参数
     * @param pwd 对象参数
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/addUser.do")
    public void add_user(HttpServletRequest req, HttpServletResponse resp,UserInfo user,String uname,String pwd)throws ServletException, IOException {
        System.out.println("添加管理员");
        req.setCharacterEncoding("UTF-8");
//        user.setPid(null);
        int flag = service.add_user(user);
        req.getRequestDispatcher("/queryUserByPage/1").forward(req,resp);
    }

    /**
     *删除
     * @param req
     * @param pid
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/delUser.do")
    public void del_orders(HttpServletRequest req, int pid,HttpServletResponse resp)throws ServletException, IOException{
        System.out.println("删除管理员");
        req.setCharacterEncoding("UTF-8");
        service.del_user(pid);
        req.getRequestDispatcher("/queryUserByPage/1").forward(req,resp);
    }

    /**
     * 修改
     * @param req
     * @param resp
     * @param count
     * @param user
     * @param pid1
     * @param uname
     * @param pwd
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/updateUser.do")
    public void update_user(HttpServletRequest req, HttpServletResponse resp,String count,UserInfo user,String pid1,String uname,String pwd)throws ServletException, IOException {
        System.out.println("\n修改管理员信息"+count);
        req.setCharacterEncoding("UTF-8");

        if (count==null) {
            int pid = Integer.parseInt(pid1);
            UserInfo user1 = service.getUserInfoOne(pid); //查找该该id号的user对象
            /**req.setAttribute(" aa" ,aa)是将aa对象存入到request中，给它起了个名字叫"aa"
             req.setAttribute( "aa" , "aa" )是直接将字符串"aa"存入到request中，给它起了个名字叫"aa"*/
            req.setAttribute("user", user1);

            System.out.println("先找到改对象\t用户ID:"+user1.getPid()+"\t用户名:"+user1.getUname()+"\t密码:"+user1.getPwd());

            req.getRequestDispatcher("/view/form-basic/User_Form.jsp").forward(req, resp);   //请求转发到表单页面
        }

        if(count!=null&&count.equals("2")) {
            System.out.println("第二次进来，直接修改信息");
            //说明进行的是修改操作,第二次进来，直接修改
            int flag = service.Update_user(user);
            req.getRequestDispatcher("/queryUserByPage/1").forward(req,resp);
        }

    }
}
