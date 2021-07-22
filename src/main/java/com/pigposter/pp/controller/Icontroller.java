package com.pigposter.pp.controller;
import com.pigposter.pp.model.*;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javafx.geometry.Pos;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;

import com.pigposter.pp.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
@RestController
@RequestMapping("/i")
public class Icontroller {
    @Autowired
    AccountRepository acr;
    @Autowired
    AvatarRepository avr;
    @Autowired
    BindRepository abr;
    @Autowired
    WorkSheetRepository wsr;
    @Autowired
    FollowRepository flr;
    @Autowired
    UserRepository usr;
    @Autowired
    PosterRepository psr;
    
    UserController uc;
    @GetMapping("/follow")
    public String follow(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password,@RequestParam("fol")String fol) throws Exception
    {
        Account ac = uc.login(username, password);
        if(ac == null) return "bad login";
        Follow f = new Follow();
        f.setFollower(ac.getUser());
        f.setFollowee(usr.findUserByUsername(fol));
        flr.save(f);
        return "ok";
    }
    @GetMapping("/userinfo")
    public User userinfo(@RequestParam("username")String username)
    {
        return usr.findUserByUsername(username);
    }
    @GetMapping("/timeline")
    public List<Poster> timeline(@RequestParam("username")String username)
    {
        return psr.findPostersByUser(usr.findUserByUsername(username));
    }
    @GetMapping("/myView")
    public List<Poster> myview(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password) throws Exception
    {
        Account ac = uc.login(username, password);
        if(ac ==null) return new ArrayList<Poster>();
        List<User> u = uc.following(username, password);
        List<Poster> p = new ArrayList<Poster>();
        for(int i = 0;i < u.size();i++)
        {
            p.addAll(timeline(u.get(i).getUsername()));
        }
        Collections.sort(p,new Comparator<Poster>()
        {
            @Override
            public int compare(Poster a,Poster b)
            {
                Integer date = Integer.valueOf(String.valueOf(a.getTime()).substring(0, 10));
                Integer date2 = Integer.valueOf(String.valueOf(b.getTime()).substring(0, 10));
                return date.intValue() - date2.intValue();
            }
        });
        return p;
    }
}
