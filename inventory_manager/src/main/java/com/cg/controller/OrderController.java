package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping
    public ModelAndView listOrders() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/order/list");
        return modelAndView;
    }

}
