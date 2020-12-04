package com.pinux.config.security.jwt;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.pinux.config.security.IgnoredUrlsProperties;
import com.pinux.constant.SecurityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ZENGGY
 * @date: 2018/11/28 12:53
 * @Description: (用一句话描述该文件做什么)
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        PathMatcher pathMatcher = new AntPathMatcher();
        //除配置文件忽略路径token检查
        for (String url : ignoredUrlsProperties.getUrls()) {
            if (pathMatcher.match(url, request.getServletPath())) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        String header = request.getHeader(SecurityConstant.HEADER);
        if (StringUtils.isEmpty(header)) {
            header = request.getParameter(SecurityConstant.HEADER);
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request, response);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            e.toString();
        }

        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String token = request.getHeader(SecurityConstant.HEADER);
        if (!StringUtils.isEmpty(token)) {
            String userStr = redisTemplate.opsForValue().get("PINUX::USER::TOKEN" + token);
            if (StringUtils.isEmpty(userStr)) {
                throw new Exception("登录已失效，请重新登录");
            } else {
                String username = JSONObject.parseObject(userStr).getString("username");
                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                String tokenKey = "PINUX::USER::TOKEN" + token;
                String authoritiesKey = "PINUX::AUTHORITIES::LIST::" + username;
                //刷新token有效期
                redisTemplate.expire(tokenKey, 2L, TimeUnit.HOURS);
                redisTemplate.expire(authoritiesKey, 2L, TimeUnit.HOURS);
                String authority = redisTemplate.opsForValue().get(authoritiesKey);
                if (!StringUtils.isEmpty(authority)) {
                    List<String> list = new Gson().fromJson(authority, new TypeToken<List<String>>() {
                    }.getType());
                    for (String ga : list) {
                        authorities.add(new SimpleGrantedAuthority(ga));
                    }
                }
                if (!StringUtils.isEmpty(username)) {
                    // 此处password不能为null
                    User principal = new User(username, "", authorities);
                    return new UsernamePasswordAuthenticationToken(principal, null, authorities);
                }
            }
        }

        return null;
    }
}
