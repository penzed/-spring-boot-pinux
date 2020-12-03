package com.pinux.config.security.jwt;

import com.pinux.service.user.UserService;
import com.pinux.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author somebody
 */
@Component
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    Logger logger = LoggerFactory.getLogger(AuthenticationFailHandler.class);
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        //登录次数限制
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "用户名或密码错误"));
        } else if (e instanceof DisabledException) {
            ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "账户被禁用，请联系管理员"));
        } else {
            ResponseUtil.out(response, ResponseUtil.resultMap(false, 500, "登录失败，其他内部错误"));
        }

    }


}
