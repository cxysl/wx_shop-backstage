package com.cxysl.controller;

import com.cxysl.comm.Page;
import com.cxysl.entity.Goods;
import com.cxysl.entity.GoodsPurchase;
import com.cxysl.service.GoodsPurchaseSrevie;
import com.cxysl.service.GoodsServie;
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
public class GoodsServlet {
    @Autowired
    private GoodsServie service;
    @Autowired
    private GoodsPurchaseSrevie service2;

    /**
     * 分页查
     * @param pageid
     * @return
     */
    @RequestMapping("/queryProByPage/{page}")
    public ModelAndView getProductByPage(@PathVariable("page") Integer pageid){
        Page<Goods> page = new Page<>();
        ModelAndView modelAndView = new ModelAndView();
        if(null!=pageid||pageid.intValue()!=0)
        {
            page.setCurenPage(pageid);
        }
        page = service.queryAll(page);
        modelAndView.addObject("page",page);

        modelAndView.setViewName("index");
        return modelAndView;
    }

    /**
     * 分页查已上架商品
     * @param pageid
     * @return
     */
    @RequestMapping("/queryUpProByPage/{page}")
    public ModelAndView getUpProductByPage(@PathVariable("page") Integer pageid){
        Page<Goods> page = new Page<>();
        System.out.println("查询成功");
        ModelAndView modelAndView = new ModelAndView();
        if(null!=pageid||pageid.intValue()!=0)
        {
            page.setCurenPage(pageid);
        }
        page = service.queryUpAll(page);
        modelAndView.addObject("page",page);

        modelAndView.setViewName("table/GoodsUpDate");
        return modelAndView;
    }

    /**
     * 增加商品信息
     * @param req
     * @param resp
     * @param goods
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/addGoods.do")
    public void add_Goods(HttpServletRequest req, HttpServletResponse resp, Goods goods)throws ServletException, IOException {

        System.out.println("添加商品");
        req.setCharacterEncoding("UTF-8");

        if(goods.getGoodsPicture().length()==0){         //  默认图片
            goods.setGoodsPicture("http://101.200.144.60:9000/images/WX_img/none.jpg");
        }
        int flag = service.add_product(goods);      /**增加商品     **/

/**增加采购信息     **/
        SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy-MM-dd  HH:mm");
        String PurchaseDate =   dateFormat.format( new Date() );        //采购时间
        System.out.println("商品ID:"+goods.getGoodsId()+"\t商品名称:"+goods.getGoodsName()+
                "\t采购价:"+goods.getPurchasePrice()+"\t采购数量:"+goods.getGoodsCount()+"\t采购时间:"+PurchaseDate);

        GoodsPurchase purchase = new GoodsPurchase();
    //赋值
        purchase.setGoodsName(goods.getGoodsName());
//        purchase.setGoodsId(goods.getGoodsId());
        purchase.setPurchaseCount(goods.getGoodsCount());
        purchase.setPurchasePrice(goods.getPurchasePrice());
        purchase.setPurchaseDate(PurchaseDate);
        int flag2 = service2.add_Purchase(purchase);    //增加采购记录


        req.getRequestDispatcher("/queryProByPage/1").forward(req,resp);
    }

    /**
     * 删除
     * @param req
     * @param goodsId
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/delGoods.do")
    public void del_Goods(HttpServletRequest req, int goodsId,HttpServletResponse resp)throws ServletException, IOException{
        System.out.println("删除商品");
        req.setCharacterEncoding("UTF-8");
        service.del_product(goodsId);
        req.getRequestDispatcher("/queryProByPage/1").forward(req,resp);
    }

    /**
     *修改商品完整信息
     * @param req
     * @param resp
     * @param countgoods
     * @param goods
     * @param goodsId1
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/updateGoods.do")
    public void update_Goods(HttpServletRequest req, HttpServletResponse resp,String countgoods, Goods goods, String goodsId1)throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        System.out.println("\n修改商品信息"+countgoods);


        if (countgoods==null){
            int goodsId = Integer.parseInt(goodsId1);
            Goods Goods1 = service.getGoodsOne(goodsId);//查找该该id号的Goods1对象

            req.setAttribute("goods", Goods1);

            System.out.println("先找到改对象\t商品ID:"+Goods1.getGoodsId()+"\t商品名称:"+Goods1.getGoodsName()+"\t商品价格:"+Goods1.getGoodsPrice());

            req.getRequestDispatcher("/view/form-basic/Goods_Form.jsp").forward(req, resp);   //请求转发到表单页面
        }

        if(countgoods!=null&&countgoods.equals("2")) {
            System.out.println("第二次进来，直接修改信息");
            //说明进行的是修改操作,第二次进来，直接修改

            if(goods.getGoodsPicture().length()==0){
                goods.setGoodsPicture("http://101.200.144.60:9000/images/WX_img/none.jpg");
            }
            int flag = service.Update_product(goods);
            req.getRequestDispatcher("/queryProByPage/1").forward(req,resp);
        }

    }

    /**采购改库存量
     *
     * @param req
     * @return
     */
    @RequestMapping("/updateGoodsCount.do")
    public void Update_GoodsCount(HttpServletRequest req,HttpServletResponse resp,String goodsCount2,String countgoods, GoodsPurchase purchase, String goodsId2) throws IOException, ServletException {

        req.setCharacterEncoding("UTF-8");
        System.out.println("\n修改商品信息"+countgoods);
req.s
        if (countgoods==null){
            int goodsId3 = Integer.parseInt(goodsId2);
            Goods Goods1 = service.getGoodsOne(goodsId3);//查找该该id号的Goods1对象
            req.setAttribute("goods", Goods1);

            System.out.println("先找到改对象\t商品ID:"+Goods1.getGoodsId()+"\t\t商品名称:"+Goods1.getGoodsName()+"\t\t商品原库存量:"+Goods1.getGoodsCount());

            req.getRequestDispatcher("/view/form-basic/goodsCount_Form.jsp").forward(req, resp);   //请求转发到表单页面
        }

        if(countgoods!=null&&countgoods.equals("2")) {
            System.out.println("第二次进来，直接加库存");//说明进行的是修改操作,第二次进来，直接修改

            Goods goods1 = service.getGoodsOne(Integer.parseInt(goodsId2));//查找该该id号的Goods1对象
            int old = goods1.getGoodsCount();
            int goodsCount1 = Integer.parseInt(goodsCount2);
            System.out.println("商品原库存量:"+old+"加\t"+goodsCount1);
            old = old+goodsCount1;
            System.out.println(old);
            System.out.println(goods1.getGoodsId());

            int flag = service.Update_GoodsCount(goods1,old);

/**增加采购信息     **/
            SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy-MM-dd  HH:mm");
            String PurchaseDate =   dateFormat.format( new Date() );        //采购时间
            System.out.println("商品ID:"+goods1.getGoodsId()+"\t商品名称:"+goods1.getGoodsName()+
                    "\t采购价:"+goods1.getPurchasePrice()+"\t采购数量:"+goodsCount1+"\t采购时间:"+PurchaseDate);

//
            //赋值
            purchase.setGoodsName(goods1.getGoodsName());
            purchase.setGoodsId(goods1.getGoodsId());
            purchase.setPurchaseCount(goodsCount1);
            purchase.setPurchasePrice(goods1.getPurchasePrice());
            purchase.setPurchaseDate(PurchaseDate);

            System.out.println("商品ID:"+purchase.getGoodsId()+"\t商品名称:"+purchase.getGoodsName()+
                    "\t采购价:"+purchase.getPurchasePrice()+"\t采购数量:"+goodsCount1+"\t采购时间:"+PurchaseDate);
            int flag2 = service2.add_Purchase(purchase);    //增加采购记录

            req.getRequestDispatcher("/queryProByPage/1").forward(req,resp);
        }

    }

    /**
     * 商品上下架
     * @param req
     * @param resp
     * @param goodsId3
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping("/Update_GoodsUpdates.do")
    public void Update_GoodsUpdates(HttpServletRequest req,HttpServletResponse resp, String type,String goodsId3) throws IOException, ServletException {
        String new1 = null;
        Goods goods = service.getGoodsOne(Integer.parseInt(goodsId3));//找到该对象
        System.out.println("切换商品ID:"+goods.getGoodsId()+"\t\t商品名称:"+goods.getGoodsName());
        String old = goods.getGoodsUpdates();       //上下架状态原始值
        if(old.equals("n")){
            new1 = "y";
        }
        else{
            new1 = "n";
        }
        int flag = service.Update_GoodsUpdates(goods,new1);

        if(type.equals("2")){
            req.getRequestDispatcher("/queryUpProByPage/1").forward(req,resp);
        }else
        req.getRequestDispatcher("/queryProByPage/1").forward(req,resp);
    }
}
