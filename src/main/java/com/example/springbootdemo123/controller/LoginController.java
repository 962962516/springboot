package com.example.springbootdemo123.controller;

import com.example.springbootdemo123.entity.UserBean;
import com.example.springbootdemo123.service.UserService;
import com.example.springbootdemo123.sha256.PasswordEncoder;
import com.example.springbootdemo123.util.TokenUtil;
import io.swagger.annotations.Api;
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
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Controller
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
@Api(tags = "实现登陆注册",description = "登陆和注册")
public class LoginController {

    //将Service注入Web层
    @Resource
    UserService userService;
    @Resource
    TokenUtil tokenUtil;

    @ApiOperation("登陆页面")
    //实现登陆
    @RequestMapping("/login")
    public String show() {
        return "login";
    }

    @ApiOperation("实现登陆")
    @RequestMapping(value = "/loginIn", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestParam("name") String username, @RequestParam("password") String password, HttpServletResponse response)
            throws NoSuchAlgorithmException {
        String hashedPassword = PasswordEncoder.encode(password);
        UserBean userBean = userService.findUserByNameAndPassword(username, hashedPassword);
        if (userBean != null) { //登陆成功
            String token = tokenUtil.getToken(userBean.getName(), userBean.getPassword()); //生成token
            log.info("用户认证成功，成功生成Token");
            System.out.println(token);
            userService.saveToken(token); //存储token
            log.info("token存储成功");
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("登陆失败");
        }
    }

    @ApiOperation("注册页面")
    //注册
    @RequestMapping("/signup")
    public String ShowSignUpPage() {
        return "signup";
    }

    @ApiOperation("实现注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> signUp(@RequestParam("username") String username, @RequestParam("password") String password
                                        ,@RequestParam(value = "mail", required = false) String mail, @RequestParam(value = "PhoneNumber", required = false) String PhoneNumber)
            throws NoSuchAlgorithmException {
        String hashedPassword = PasswordEncoder.encode(password);
        userService.saveUser(username, hashedPassword,mail,PhoneNumber);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("注册成功");
    }

}

