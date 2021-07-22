package com.pigposter.pp.controller;
import com.pigposter.pp.model.*;
import com.pigposter.pp.repo.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
@RestController
@RequestMapping("/hello")
public class helloworld {
    @Autowired
    AvatarRepository aa ;

    @GetMapping("/a")
    public String hello()
    {
        return "我蛤蛤大笑";
    }
    @Autowired
    UserRepository uu ;
    @Autowired
    AccountRepository ac ;
    @GetMapping("/b")
    public Account test()
    {
        
        Avatar a = new Avatar();
        User u = new User();
        a.setPath("absquq");
        //aa.save(a);
        //Avatar q = aa.findAvatarById(1);
        u.setAvatar(a);
        u.setEmail("1");
        u.setGender(1);
        u.setMotto("ban kai");
        u.setPower(1);
        u.setUsername("littleprincess");
        Account b = new Account();
        b.setUsername("LP");
        b.setPassword("***");
        b.setUser(u);
        b.setRegisterTime(new Date());
        ac.save(b);
        //uu.save(u);
        return b;
    }
    @GetMapping("/3")
    public Account aaaaa()
    {
        Account ret = ac.findAccountByUsernameAndPassword("LP2", "***");
        return ret;
    }
}
