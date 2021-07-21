package com.pigposter.pp.controller;
import com.pigposter.pp.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pigposter.pp.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    AccountRepository acr;
    @GetMapping("/login")
    public Account login(String username,String password) throws Exception
    {
        return acr.findAccountByUsernameAndPassword(username,cookie.invParse(password));
    }
    @GetMapping("/register")
    public void register(String username,String password)
    {
        User u = new User();
        u.setAvatar(new Avatar());
        u.setEmail("");
        u.setGender(0);
        u.setMotto("I HAVE BEEN CALLED. I MUST ANSWER.ALWAYS");
        u.setPower(1);
        u.setUsername(username);
        Account a = new Account();
        a.setPassword(password);
        a.setRegisterTime(new Date());
        a.setUser(u);
        a.setUsername(username);
        acr.save(a);
    }
}
