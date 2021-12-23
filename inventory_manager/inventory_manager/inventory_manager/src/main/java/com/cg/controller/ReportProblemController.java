package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/report_problems")
public class ReportProblemController {

    @GetMapping
    public ModelAndView listReportProblems() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/report_problem/list");
        return modelAndView;
    }
}