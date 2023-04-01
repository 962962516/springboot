package com.example.springbootdemo123.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {
    @Value("${token.privateKey}")
    private String privateKey;

    //加密 token
    public String getToken(String username, String password){
        //方到负载payLoad里面，魔法值可以直接使用常量类进行封装
        String token = JWT
                .create()
                .withClaim("userId",username)
                .withClaim("userRole",password)
                .withClaim("timeStamp",System.currentTimeMillis())
                .sign(Algorithm.HMAC256(privateKey));
        return token;
    }

    //解析token
    public Map<String, String> parseToken(String token){
        HashMap<String,String> map = new HashMap<>();
        DecodedJWT decodedjwt = JWT.require(Algorithm.HMAC256(privateKey))
                .build().verify(token);
        Claim userId = decodedjwt.getClaim("userId");
        Claim userRole = decodedjwt.getClaim("userRole");
        Claim timeStamp = decodedjwt.getClaim("userRole");
        map.put("userId",userId.asString());
        map.put("userRole",userRole.asString());
        map.put("timeStamp", timeStamp.asString());
        return map;
    }

    //返回当前token的用户名
    public String getUserNameToken(String token){
        HashMap<String,String> map = new HashMap<>();
        DecodedJWT decodedjwt = JWT.require(Algorithm.HMAC256(privateKey))
                .build().verify(token);
        Claim username = decodedjwt.getClaim("userId");
        Claim password = decodedjwt.getClaim("userRole");
        Claim timeStamp = decodedjwt.getClaim("userRole");
        map.put("userId",username.asString());
        map.put("userRole",password.asString());
        map.put("timeStamp", timeStamp.asString());
        return username.asString();
    }

    
}
