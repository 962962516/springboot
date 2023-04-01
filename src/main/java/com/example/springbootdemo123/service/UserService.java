package com.example.springbootdemo123.service;

import com.example.springbootdemo123.entity.UserBean;
import com.example.springbootdemo123.mapper.UserMapper;
import com.example.springbootdemo123.sha256.PasswordEncoder;
import lombok.Data;
import lombok.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;


@Service
public class UserService {
    //将dao层属性注入service层
   @Resource
    private UserMapper userMapper;

   public UserBean findUserByNameAndPassword(String name, String password){
       return userMapper.getUserByNameAndPassword(name,password);
   }

   public void saveUser(String name, String password,String mail, String phoneNumber) throws NoSuchAlgorithmException {
       String hashedPassword = PasswordEncoder.encode(password);
       if(mail==null){
           mail="";
       }
       if(phoneNumber==null){
           phoneNumber="";
       }
       userMapper.saveUser(name, password,mail,phoneNumber);
   }


   public void saveToken(String token) {
       userMapper.saveToken(token);
   }

   public void saveMail(String name, String mail){
       userMapper.saveMail(name, mail);
   }

   public void savePhoneNumber(String name, String PhoneNumber){
       userMapper.savePhoneNumber(name, PhoneNumber);
   }

   public int searchToken(String token) {
       return userMapper.searchToken(token);
   }
}
