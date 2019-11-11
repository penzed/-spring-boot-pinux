package com.pinux.config.security;

import com.pinux.config.filter.AfterLoginFilter;
import com.pinux.config.filter.AtLoginFilter;
import com.pinux.config.filter.BeforeLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @ClassName WebSecurityConfig
 * @Description TODO
 * @Author pinux
 * @Date 2019/11/10 1:47
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(new BeforeLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(new AfterLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(new AtLoginFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests() // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/login").permitAll()// 设置所有人都可以访问/login这个URL
                .antMatchers("/**").permitAll()// 设置所有人都可以访问/login这个URL
                .antMatchers("/test/**","/test1/**").permitAll()// 所有/test/下的所有文件都为白名单
                .antMatchers("/res/**/*.{js,html}").permitAll()//把/res/的所有.js,html设置为白名单
                .anyRequest().authenticated()  // 任何请求,登录后可以访问
                .and()
                .formLogin().loginPage("/login")
        ;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
