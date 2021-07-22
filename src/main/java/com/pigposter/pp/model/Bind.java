package com.pigposter.pp.model;

import javax.persistence.*;
@Entity
@Table(name = "bind")
public class Bind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int plant;
    private String username;
    private String password;
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除文章，不影响用户
    @JoinColumn(name="uid")//设置在article表中的关联字段(外键)
    private User user;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPlant() {
        return plant;
    }
    public void setPlant(int plant) {
        this.plant = plant;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
}
