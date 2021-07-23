package com.pigposter.pp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping("/authorise")
    public String authorise(Model model)
    {
        return "authorise";
    }
    @GetMapping("/bind")
    public String bind(Model model)
    {
        return "bind";
    }
    @GetMapping("/home")
    public String home(Model model)
    {
        return "home";
    }
    @GetMapping("/poster")
    public String poster(Model model)
    {
        return "poster";
    }
    @GetMapping("/setprofile")
    public String setprofile(Model model)
    {
        return "setProfile";
    }
    @GetMapping("/setpwd")
    public String setpwd(Model model)
    {
        return "setpwd";
    }
    @GetMapping("/space")
    public String space(@RequestParam("username")String username,Model model)
    {
        return "zone";
    }
}
