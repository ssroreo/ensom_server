package com.ensomserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hi")
public class HiController {
    @RequestMapping("hello")
    public String say(ModelMap model) {
        model.addAttribute("message","helloworld");
        return "hello";
    }
}
