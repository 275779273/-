package com.itcast.controller;

import com.itcast.domain.Product;
import com.itcast.service.IProductService;
import com.itcast.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView ();
        List<Product> productList = productService.findAll ();
        mv.addObject ( "productList",productList );
        mv.setViewName ( "product-list" );
        return mv;
    }

    @RequestMapping("/save.do")
    public ModelAndView save(Product product){
        productService.save ( product );
        ModelAndView mv = findAll ();
        return mv;
    }
}
