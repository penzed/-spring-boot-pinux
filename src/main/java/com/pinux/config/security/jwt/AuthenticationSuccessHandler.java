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
        /*
        //登陆成功生成JWT
        String token = Jwts.builder()
                //主题 放入用户名
                .setSubject(username)
                //自定义属性 放入用户拥有请求权限
                .claim(SecurityConstant.AUTHORITIES, new Gson().toJson(authorities))
                //失效时间
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 1000))
                //签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.JWT_SIGN_KEY)
                .compact();
        token = SecurityConstant.TOKEN_SPLIT + token;
        */

        //改用redis存token
        String token = SecurityConstant.TOKEN_SPLIT + IdUtil.simpleUUID();//生成唯一token
        User u = userService.findByUserName(username);
        u.setPassword(null);
        redisTemplate.opsForValue().set("PINUX::USER::TOKEN" + token, JSONObject.toJSONString(u), 2L, TimeUnit.HOURS);
        String authoritiesKey = "PINUX::AUTHORITIES::LIST::" + username;
        redisTemplate.opsForValue().set(authoritiesKey, JSONObject.toJSONString(authorities), 2L, TimeUnit.HOURS);


        System.out.println("------------------登录成功");
        System.out.println("authentication = " + authentication);
        ResponseUtil.out(response, ResponseUtil.resultMap(true, 200, "登录成功", token));
    }
}
