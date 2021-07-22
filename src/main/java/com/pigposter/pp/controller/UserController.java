package com.pigposter.pp.controller;
import com.pigposter.pp.model.*;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.implementation.bytecode.constant.DefaultValue;

import com.pigposter.pp.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
@RestController
@RequestMapping("/user")
public class UserController {
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
    @GetMapping("/login")
    public Account login(String username,String password) throws Exception
    {
        return acr.findAccountByUsernameAndPassword(cookie.invParse(username),cookie.invParse(password));
    }
    @GetMapping("/register")
    public String register(@RequestParam("username")String username,
    @RequestParam("password")String password,@RequestParam("nick")String nick)
    {
        User u = new User();
        u.setAvatar(new Avatar());
        u.setEmail(nick);
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
        return username+" "+password+" "+nick;
    }
    @GetMapping("/profile")
    public Account getProfile(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password)throws Exception
    {
        return login(username,password);
    }
    @GetMapping("/modifyInfo")
    public String modifyInfo(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password,
    @RequestParam(value="avatar",defaultValue = "")String avatar,
    @RequestParam(value="motto",defaultValue = "")String motto,
    @RequestParam(value="gender",defaultValue = "0")int gender) throws Exception
    {
        Account ac = login(username,password);
        if(ac == null) return "bad login"; 
        Avatar a = ac.getUser().getAvatar();
        a.setPath(avatar);
        User u = ac.getUser();
        u.setGender(gender);
        u.setMotto(motto);
        u.setAvatar(a);
        ac.setUser(u);
        acr.save(ac);
        return "ok";
    }
    @GetMapping("/modifypass")
    public String modifyPass(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password,
    @RequestParam(value="password")String pass_) throws Exception
    {
        Account ac = login(username,password);
        if(ac == null) return "bad login"; 
        ac.setPassword(pass_);
        acr.save(ac);
        return "ok";
    }
    @GetMapping("/bind")
    public String bind(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password,
    @RequestParam(value="password")String pass_,@RequestParam(value = "username") String user_,
    @RequestParam(value="plant")int plant)throws Exception
    {
        Account ac = login(username,password);
        if(ac == null) return "bad login"; 
        Bind b = new Bind();
        b.setPlant(plant);
        b.setPassword(pass_);
        b.setUsername(user_);
        b.setUser(ac.getUser());
        abr.save(b);
        return "ok";
    }
    @GetMapping("/getbind")
    public List<Bind> getbind(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password)throws Exception
    {
        Account ac = login(username, password);
        if(ac == null) return new ArrayList<Bind>();
        return abr.findBindsByUser(ac.getUser());
    }
    @GetMapping("/up")
    public String up(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password) throws Exception
    {
        Account ac = login(username,password);
        if(ac == null) return "bad login"; 
        User u = ac.getUser();
        WorkSheet ws = new WorkSheet();
        ws.setResult(0);
        ws.setUser(u);
        ws.setTime(new Date());
        wsr.save(ws);
        return "ok";
    }
    @GetMapping("/following")
    public List<User> following(
    @CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password) throws Exception
    {
        Account ac = login(username,password);
        if(ac == null) return new ArrayList<User>();
        List<Follow> f= flr.findFollowsByFollower(ac.getUser());
        List<User> u = new ArrayList<User>();
        for(int i = 0;i < f.size();i++)
        {
            u.add(f.get(i).getFollowee());
        }
        return u;
    }
    @GetMapping("/follower")
    public List<User> follower(
    @CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password) throws Exception
    {
        Account ac = login(username,password);
        if(ac == null) return new ArrayList<User>();
        List<Follow> f= flr.findFollowsByFollowee(ac.getUser());
        List<User> u = new ArrayList<User>();
        for(int i = 0;i < f.size();i++)
        {
            u.add(f.get(i).getFollower());
        }
        return u;
    }
    
}
