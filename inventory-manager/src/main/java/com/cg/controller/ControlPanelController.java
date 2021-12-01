package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cp")
public class ControlPanelController {

    @GetMapping
    public ModelAndView getIndex() {
        return new ModelAndView("/cp/index");
    }
}
