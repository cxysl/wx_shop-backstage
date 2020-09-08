package com.cxysl.controller;

import com.cxysl.comm.Page;
import com.cxysl.entity.GoodsPicture;
import com.cxysl.service.GoodsPictureSrevie;
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
public class GoodsPictureServlet {

    @Autowired
    private GoodsPictureSrevie service;

    /**
     * 分页查
     * @param pageid
     * @return
     */
    @RequestMapping("/queryPictureByPage/{page}")
    public ModelAndView show(@PathVariable("page") Integer pageid){
        Page<GoodsPicture> page = new Page<>();
        ModelAndView modelAndView = new ModelAndView();
        if(null!=pageid||pageid.intValue()!=0)
        {
            page.setCurenPage(pageid);
        }
        page = service.queryAll(page);
        modelAndView.addObject("page",page);

        modelAndView.setViewName("table/GoodsPicture");
        return modelAndView;
    }

    /**
     * 增加
     * http://101.200.144.60:9000/images/WX_img/none.jpg
     * @param req
     * @param resp
     * @param picture 对象
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/addPicture.do")
    public void add_Picture(HttpServletRequest req, HttpServletResponse resp, GoodsPicture picture)throws ServletException, IOException {
        System.out.println("添加商品图片");
        req.setCharacterEncoding("UTF-8");
        System.out.println(picture.getPictureId());
        if(picture.getPictureOne().length()==0){
            picture.setPictureOne("http://101.200.144.60:9000/images/WX_img/none.jpg");
        }
        if(picture.getPictureTwo().length()==0){
            picture.setPictureTwo("http://101.200.144.60:9000/images/WX_img/none.jpg");
        }
        if(picture.getPictureThree().length()==0){
            picture.setPictureThree("http://101.200.144.60:9000/images/WX_img/none.jpg");
        }
        if(picture.getPictureFour().length()==0){
            picture.setPictureFour("http://101.200.144.60:9000/images/WX_img/none.jpg");
        }
        if(picture.getPictureFive().length()==0){
            picture.setPictureFive("http://101.200.144.60:9000/images/WX_img/none.jpg");
        }
        int flag = service.add_Picture(picture);
        req.getRequestDispatcher("/queryPictureByPage/1").forward(req,resp);
    }

    /**
     *删除
     * @param req
     * @param pictureId
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/delPicture.do")
    public void del_orders(HttpServletRequest req, int pictureId,HttpServletResponse resp)throws ServletException, IOException{
        System.out.println("删除商品图片");
        req.setCharacterEncoding("UTF-8");
        service.del_Picture(pictureId);
        req.getRequestDispatcher("/queryPictureByPage/1").forward(req,resp);
    }

    /**
     * 修改
     * @param req
     * @param resp
     * @param count
     * @param picture
     * @param pictureId1
     * @param uname
     * @param pwd
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/updatePicture.do")
    public void update_Picture(HttpServletRequest req, HttpServletResponse resp,String count,GoodsPicture picture,String pictureId1,String uname,String pwd)throws ServletException, IOException {
        System.out.println("\n修改商品图片"+count);
        req.setCharacterEncoding("UTF-8");

        if (count==null) {
            int pictureId = Integer.parseInt(pictureId1);
            GoodsPicture picture1 = service.getGoodsPictureOne(pictureId); //查找该该id号的picture对象
            req.setAttribute("picture", picture1);

            System.out.println("先找到改对象\t编号ID:"+picture1.getPictureId());

            req.getRequestDispatcher("/view/form-basic/GoodsPicture.jsp").forward(req, resp);   //请求转发到表单页面
        }

        if(count!=null&&count.equals("2")) {
            System.out.println("第二次进来，直接修改商品图片"+picture.getPictureTwo()+"\t"+picture.getPictureOne());
            //说明进行的是修改操作,第二次进来，直接修改

            if(picture.getPictureOne().length()==0){
                picture.setPictureOne("http://101.200.144.60:9000/images/WX_img/none.jpg");
            }
            if(picture.getPictureTwo().length()==0){
                picture.setPictureTwo("http://101.200.144.60:9000/images/WX_img/none.jpg");
            }
            if(picture.getPictureThree().length()==0){
                picture.setPictureThree("http://101.200.144.60:9000/images/WX_img/none.jpg");
            }
            if(picture.getPictureFour().length()==0){
                picture.setPictureFour("http://101.200.144.60:9000/images/WX_img/none.jpg");
            }
            if(picture.getPictureFive().length()==0){
                picture.setPictureFive("http://101.200.144.60:9000/images/WX_img/none.jpg");
            }

            int flag = service.Update_Picture(picture);
            req.getRequestDispatcher("/queryPictureByPage/1").forward(req,resp);
        }

    }
}
