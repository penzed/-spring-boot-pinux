package com.pinux.config.security.jwt;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.pinux.constant.SecurityConstant;
import com.pinux.entity.user.User;
import com.pinux.service.user.UserService;
import com.pinux.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 登录成功处理类
 *
 * @author somebody
 */
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        List<GrantedAuthority> list = (List<GrantedAuthority>) ((UserDetails) authentication.getPrincipal()).getAuthorities();
        List<String> authorities = new ArrayList<String>();
        for (GrantedAuthority g : list) {
            authorities.add(g.getAuthority());
        }
        //改用redis存token
        String token = SecurityConstant.TOKEN_SPLIT + IdUtil.simpleUUID();//生成唯一token
        User u = userService.findByUserName(username);
        u.setPassword(null);
        redisTemplate.opsForValue().set("PINUX::USER::TOKEN" + token, JSONObject.toJSONString(u), 2L, TimeUnit.HOURS);
        String authoritiesKey = "PINUX::AUTHORITIES::LIST::" + username;
        redisTemplate.opsForValue().set(authoritiesKey, JSONObject.toJSONString(authorities), 2L, TimeUnit.HOURS);
        // TODO: 2020/12/4 登录失败次数限制数据清除

        System.out.println("------------------登录成功");
        System.out.println("authentication = " + authentication);
        ResponseUtil.out(response, ResponseUtil.resultMap(true, 200, "登录成功", token));
    }
}
