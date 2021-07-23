package com.pigposter.pp.controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pigposter.pp.model.*;
import org.springframework.web.bind.annotation.CookieValue;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import com.pigposter.pp.repo.*;
import java.util.*;

@RestController
@RequestMapping("/post")

public class PosterController {
    @Autowired
    BindRepository abr;
    @Autowired
    PosterRepository por;
    @Autowired
    CommentRepository cor;
    @Autowired
    LikeRepository lkr;
    @Autowired
    MediaRepository mdr;
    @Autowired
    TagRepository tgr;
    @Autowired
    FollowRepository flr;
    @Autowired
    AccountRepository acr;
    public Account login(String username,String password) throws Exception
    {
        return acr.findAccountByUsernameAndPassword(cookie.invParse(username),cookie.invParse(password));
    }
    @PostMapping("/add")
    public String add(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password,
    @RequestParam(value ="text")String text,@RequestParam(value="tags")List<String>tag
    ,@RequestParam("media")List<String>media) throws Exception
    {
        Account ac = login(username,password);
        if(ac == null) return "bad login";
        User u = ac.getUser();
        Poster p = new Poster();
        p.setTime(new Date());
        p.setText(text);
        p.setUser(u);
        por.save(p);
        for(int i = 0 ;i < media.size();i++)
        {
            Media m = new Media();
            m.setPath(media.get(i));
            m.setPid(p);
            mdr.save(m);
        }
        for(int i = 0 ;i < tag.size();i++)
        {
            Tag m = new Tag();
            m.setText(tag.get(i));
            m.setPoster(p);
            m.setUser(u);
            tgr.save(m);
        }
        return "ok";
    }
    @GetMapping("/comment")
    public String comment(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password,
    @RequestParam("pid")int pid,@RequestParam("text")String text,@RequestParam("cid")int cid)throws Exception
    {
        Account ac = login(username,password);
        if(ac == null) return "bad login";
        Comment c = new Comment();
        c.setCommentId(cid);
        c.setPid(por.findPosterById(pid));
        c.setText(text);
        c.setTime(new Date());
        c.setUser(ac.getUser());
        cor.save(c);
        return "ok";
    }
    @GetMapping("/likeit")
    public String likeit(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password,
    @RequestParam("pid")int pid,@RequestParam("cid")int cid)throws Exception
    {
        Account ac = login(username,password);
        if(ac == null) return "bad login";
        Like c = new Like();
        c.setCon(1);
        c.setPid(por.findPosterById(pid));
        c.setTime(new Date());
        c.setUser(ac.getUser());
        lkr.save(c);
        return "ok";
    }
    @GetMapping("/dislikeit")
    public String dislikeit(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password,
    @RequestParam("pid")int pid,@RequestParam("cid")int cid)throws Exception
    {
        Account ac = login(username,password);
        if(ac == null) return "bad login";
        Like c = new Like();
        c.setCon(-1);
        c.setPid(por.findPosterById(pid));
        c.setTime(new Date());
        c.setUser(ac.getUser());
        lkr.save(c);
        return "ok";
    }
    @GetMapping("/liked")
    public List<Like> liked(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password) throws Exception
    {
        Account ac = login(username, password);
        if(ac ==null) return new ArrayList<Like>();
        return lkr.findLikesByUser(ac.getUser());
    }
    @GetMapping("/commented")
    public List<Comment> commented(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password) throws Exception
    {
        Account ac = login(username, password);
        if(ac ==null) return new ArrayList<Comment>();
        return cor.findCommentsByUser(ac.getUser());
    }

    @GetMapping("/fold")
    public String fold(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password,@RequestParam("pid")int pid) throws Exception
    {
        Account ac =login(username, password);
        if(ac == null) return "bad login";
        Poster p = por.findPosterById(pid);
        p.setFold(1);
        por.save(p);
        return "ok";
    }
    @GetMapping("/foldcomment")
    public String foldcomment(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password,@RequestParam("cid")int cid) throws Exception
    {
        Account ac = login(username, password);
        if(ac == null) return "bad login";
        Comment p = cor.findCommentById(cid);
        p.setFold(1);
        cor.save(p);
        return "ok";
    }
    @RequestMapping("/search")
    public Set<String> search(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password,@RequestParam("key")String key)
    {
        Set<String> s= new HashSet<String>();
        List<Tag> t =tgr.findTagsByText(key);
        for(int i = 0;i < t.size();i++)
        s.add(t.get(i).getText());
        return s;
    }
    @RequestMapping("/hot")
    public List<Poster> hot(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password) 
    {
        List<Poster> p= por.findAll();
        Collections.sort(p,new Comparator<Poster>()
        {
            @Override
            public int compare(Poster a,Poster b)
            {
                return lkr.findLikesByPid(a).size() - lkr.findLikesByPid(b).size();
                // return a.getLikes().size() - b.getLikes().size();
            }
        });
        return p;
    }
    @RequestMapping("/tagposter")
    public List<Poster> tagposter(@CookieValue(value="unm",defaultValue = "")String username
    ,@CookieValue(value="ppp",defaultValue = "")String password,@RequestParam("tag")String tag)
    {
        List<Poster> ans = new ArrayList<Poster>();
        List<Poster> p= por.findAll();
        for(int i = 0;i < p.size();i++)
        {
            List<Tag> t = tgr.findTagsByPid(p.get(i));
            int f = 0;
            for(int j = 0; j< t.size();j++)
            {
                if(t.get(j).getText() == tag) f= 1;
            }
            if(f==1) ans.add(p.get(i));
        }
        return ans;
    }
}

