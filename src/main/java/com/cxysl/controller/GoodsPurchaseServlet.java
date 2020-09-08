package com.cxysl.controller;

import com.cxysl.comm.Page;
import com.cxysl.entity.GoodsPurchase;
import com.cxysl.service.GoodsPurchaseSrevie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GoodsPurchaseServlet {

    @Autowired
    private GoodsPurchaseSrevie service;

    /**
     * 分页查
     * @param pageid
     * @return
     */
    @RequestMapping("/queryPurByPage/{page}")
    public ModelAndView getProductByPage(@PathVariable("page") Integer pageid){
        Page<GoodsPurchase> page = new Page<>();
        ModelAndView modelAndView = new ModelAndView();
        if(null!=pageid||pageid.intValue()!=0)
        {
            page.setCurenPage(pageid);
        }
        page = service.queryAll(page);
        modelAndView.addObject("page",page);

        modelAndView.setViewName("/table/GoodsPurchase");
        return modelAndView;
    }
}
