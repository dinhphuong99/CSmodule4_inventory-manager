package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/items")
public class ItemController {

    @GetMapping
    public ModelAndView listItems() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/item/list");
        return modelAndView;
    }

}
