package com.example.springbootdemo123.filter;


import com.example.springbootdemo123.exception.TokenAuthExpiredException;
import com.example.springbootdemo123.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    TokenUtil tokenUtil;
    @Value("${token.privateKey}")
    private String privateKey;
    @Value("${token.yangToken}")
    private Long yangToken;
    @Value("${token.oldToken}")
    private Long oldToken;

    //权限认证的拦截操作
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object)
        throws Exception{
        log.info("======进入拦截器======");
        //如果不是映射到方法直接通过，可以访问资源
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        //为空就返回错误
        String token = httpServletRequest.getHeader("token");
        if(null == token || "".equals(token.trim())){
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token不存在，请重新登陆");
            return false;
        }
        log.info("============token:"+token);
        Map<String,String> map = tokenUtil.parseToken(token);
        String userId = map.get("userId");
        String userRole = map.get("userRole");
        long timeOfUse = System.currentTimeMillis() - Long.parseLong(map.get("timeStamp"));

        //判断token是否过期
        //yong token
        if(timeOfUse < yangToken){
            log.info("yong token");
        }
        //old token 刷新 token
        else if (timeOfUse >= yangToken) {
            httpServletResponse.setHeader("token",tokenUtil.getToken(userId,userRole));
        }
        //过期 token 返回 token 无效
        else{
            throw new TokenAuthExpiredException();
        }

        //角色匹配
        if("user".equals(userRole)){
            log.info("======user账户======");
            return true;
        }
        if("admin".equals(userRole)){
            log.info("======admin账户======");
            return true;
        }
        return false;
    }


}
