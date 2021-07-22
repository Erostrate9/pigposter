package com.pigposter.pp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import com.pigposter.pp.repo.*;

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
    // @GetMapping("/add")
    // public String add(@CookieValue(value="unm",defaultValue = "")String username
    // ,@CookieValue(value="ppp",defaultValue = "")String password,
    // @RequestParam(value ="text")String text,@RequestParam(value="media")String media
    // ,@RequestMapping(value="tags")List<String>Tag) throws Exception
    // {

    // }
}

