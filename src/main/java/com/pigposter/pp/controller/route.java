package com.pigposter.pp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
@Controller
@RequestMapping("/route")
public class route {
    @GetMapping("/quq")
    public String login(Model model)
    {
        return "login";
    }
    @GetMapping("/register")
    public String lgin(Model model)
    {
        return "signup";
    }
}
