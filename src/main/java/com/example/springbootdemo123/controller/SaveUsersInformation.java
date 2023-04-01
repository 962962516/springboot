package com.example.springbootdemo123.controller;

import com.example.springbootdemo123.service.UserService;
import com.example.springbootdemo123.util.TokenUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;


@Slf4j
@Controller
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
public class SaveUsersInformation {
    @Resource
    UserService userService;
    @Resource
    TokenUtil tokenUtil;

    @ApiOperation("存储邮箱页面")
    @RequestMapping("/saveUserMail")
    public String saveUserMail(){
        return "saveUserMail";
    }

    @ApiOperation("存储用户邮箱")
    @RequestMapping(value = "/saveMail", method = RequestMethod.POST)
    public ResponseEntity<String> saveMail(@RequestParam("token") String token,@RequestParam("mail") String userMail)
            throws NoSuchAlgorithmException {

            if(userService.searchToken(token)!=0) {//比对token是否存在
                log.info("token验证成功");
                //获取当前token用户名
                String username = tokenUtil.getUserNameToken(token);
                System.out.println(username);
                userService.saveMail(username,userMail);
                log.info("存储邮箱成功");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("存储邮箱成功");
            }else{
            log.warn("token不存在");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token错误");
        }
    }

    @ApiOperation("存储邮箱页面")
    @RequestMapping("/savePhoneNumber")
    public String saveUserPhone(){
        return "savePhoneNumber";
    }
    @ApiOperation("存储手机号")
    @RequestMapping(value = "/saveUserPhone",method = RequestMethod.POST)
    public ResponseEntity<String> savePhoneNumber(@RequestParam("token") String token, @RequestParam("phoneNumber") String userPhoneNumber)
            throws NoSuchAlgorithmException{

        if(userService.searchToken(token)!=0) {//比对token是否存在
            log.info("token验证成功");
            //获取当前token用户名
            String username = tokenUtil.getUserNameToken(token);
            System.out.println(username);
            userService.savePhoneNumber(username,userPhoneNumber);
            log.info("存储手机号成功");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("存储手机号成功");
        }else{
            log.warn("token不存在");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token错误");
        }

    }

}
