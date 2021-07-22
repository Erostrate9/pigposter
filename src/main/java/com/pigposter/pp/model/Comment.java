package com.pigposter.pp.model;

import javax.persistence.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="uid")
    private User user;
    private Date time;
    private String text;
    private int fold;
    private int commentId;
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    //@JoinColumn(name="pid")
    private Poster pid;
    public void setPid(Poster pid)
    {
        this.pid = pid;
    }
    public Poster getPid()
    {
        return pid;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public int getFold() {
        return fold;
    }
    public void setFold(int fold) {
        this.fold = fold;
    }
    public int getCommentId() {
        return commentId;
    }
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
    
}
