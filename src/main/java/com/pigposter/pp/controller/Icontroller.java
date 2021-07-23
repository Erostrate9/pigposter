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
    @Autowired
    LikeRepository lkr;
    @Autowired
    CommentRepository cmr;
    @Autowired
    MediaRepository mdr;
    public Account login(String username,String password) throws Exception
    {
        return acr.findAccountByUsernameAndPassword(cookie.invParse(username),cookie.invParse(password));
    }
    UserController uc = new UserController();
    @GetMapping("/follow")
    public String follow(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password,@RequestParam("fol")String fol) throws Exception
    {
        Account ac = login(username, password);
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
    @GetMapping("/myview")
    public List<Poster> myview(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password) throws Exception
    {
        Account ac = login(username, password);
        //uc.login(username, password);
        if(ac ==null) return new ArrayList<Poster>();
        List<User> u = following(username, password);
        List<Poster> p = new ArrayList<Poster>();
        for(int i = 0;i < u.size();i++)
        {
            p.addAll(timeline(u.get(i).getUsername()));
        }
        // Collections.sort(p,new Comparator<Poster>()
        // {
        //     @Override
        //     public int compare(Poster a,Poster b)
        //     {
        //         Integer date = Integer.valueOf(String.valueOf(a.getTime()).substring(0, 10));
        //         Integer date2 = Integer.valueOf(String.valueOf(b.getTime()).substring(0, 10));
        //         return date.intValue() - date2.intValue();
        //     }
        // });
        // Date d = new Date();
        // d.setTime(0);
        // List<Poster> pro = psr.findPostersByTime(d);
        // Collections.shuffle(pro);
        // for(int i = 0;i < (pro.size()<10?pro.size():10);i++) 
        // {
        //     if(i+10 >= p.size()) break;
        //     p.add(i+10,pro.get(i));
        // }
        return p;
    }
    @GetMapping("/getlike")
    public List<Like> getlick(int pid)
    {
        Poster p = psr.findPosterById(pid);
        return lkr.findLikesByPid(p);
    }
    @GetMapping("/getmedia")
    public List<Media> getmedia(int pid)
    {
        Poster p = psr.findPosterById(pid);
        return mdr.findMediasByPid(p);
    }
    @GetMapping("/getcomment")
    public List<Comment> getcomment(int pid)
    {
        Poster p = psr.findPosterById(pid);
        return cmr.findCommentsByPid(p);
    }
    @GetMapping("/upn")
    public int upn(@RequestParam("username") String username)
    {
        List<Poster>a = psr.findPostersByUser(usr.findUserByUsername(username));
        if(a==null) return 0;
        else 
        return a.size();
    }
    @GetMapping("/ufollowing")
    public int ufn(@RequestParam("username") String username)
    {
        List<Follow>a = flr.findFollowsByFollower(usr.findUserByUsername(username));
        if(a==null) return 0;
        else 
        return a.size();
    }
    @GetMapping("/ufollower")
    public int ufen(@RequestParam("username") String username)
    {
        List<Follow>a = flr.findFollowsByFollowee(usr.findUserByUsername(username));
        if(a==null) return 0;
        else 
        return a.size();
    }
    @GetMapping("/gpath")
    public List<String> us(@RequestParam("username") String username)
    {
        List<String> ans = new ArrayList<String>();
        List<Poster> p = psr.findPostersByUser(usr.findUserByUsername(username));
        for(int i = 0;i < p.size();i++)

        {List<Media> a = mdr.findMediasByPid(p.get(i));
        for(int j = 0; j< a.size();j++)
        {

        String ss = a.get(j).getPath();
        if(ss.substring(ss.length()-3).equals("mp4"))
        continue;
        else
        ans.add(a.get(j).getPath());
        }
        }
        return ans;
    }
}
