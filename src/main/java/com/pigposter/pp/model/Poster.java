package com.pigposter.pp.model;

import javax.annotation.Generated;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;
import java.util.*;
@Entity
@Table(name = "poster")
public class Poster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;

    @OneToMany(mappedBy = "pid")
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "pid",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Like> likes;    
    @OneToMany(mappedBy = "pid",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Tag> tags;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @JoinColumn(name="uid")//设置在article表中的关联字段(外键)
    private User user;

    @OneToMany(mappedBy = "pid",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Media> file;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date time;
    private int fold;
    public int getFold()
    {
        return fold;
    }
    public void setFold(int fold)
    {
        this.fold = fold;
    }
    public Date getTime()
    {
        return time;
    }
    public void setTime(Date time)
    {
        this.time = time;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Media> getFile() {
        return file;
    }

    public void setFile(List<Media> file) {
        this.file = file;
    }
    
}
