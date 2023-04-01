package com.example.springbootdemo123.entity;

import javax.annotation.Resource;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;


public class UserBean implements Serializable {

    private Integer id;
    private String name;
    private String password;
    @Email(message = "Invalid email address")
    @Size(max = 255)
    private String email;

    @Size(max = 20)
    private String PhoneNumber;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }
}
