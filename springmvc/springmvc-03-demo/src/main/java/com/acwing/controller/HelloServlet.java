package com.acwing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/e1")
public class HelloServlet {

    @RequestMapping("/t1")
    String hello(Model model){
        System.out.println("Hello");
        model.addAttribute("msg","xnn");
        return "test";
    }
}
