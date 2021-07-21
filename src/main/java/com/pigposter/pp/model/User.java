package com.pigposter.pp.model;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
@Entity
@Table(name = "User")
public class User {
    @Id
    private String username;

    private String email;
    private String motto;
    @OneToOne(cascade = CascadeType.ALL)
    private Avatar avatar;
    private int power;
    private int gender;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMotto() {
        return motto;
    }
    public void setMotto(String motto) {
        this.motto = motto;
    }
    public Avatar getAvatar() {
        return avatar;
    }
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }

}
