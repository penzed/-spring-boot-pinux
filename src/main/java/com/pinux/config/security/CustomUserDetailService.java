package com.pinux.config.security;

import com.pinux.entity.role.Role;
import com.pinux.entity.user.UserInfo;
import com.pinux.service.role_user.RoleUserService;
import com.pinux.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
 * @ClassName CustomUserDetailService
 * @Description TODO 登录权限修改
 * @Author pinux
 * @Date 2019/11/10 5:44
 * @Version 1.0
 */
@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RoleUserService roleUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("CustomUserDetailService.loadUserByUsername()");
        //通过username获取用户信息
        UserInfo userInfo = userInfoService.findByName(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("not found");
        }
        List<Role> roles=roleUserService.getRolesByUserName(username);
        //定义权限列表.
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (roles!=null){
            for (Role role:roles){
                // 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
            }
        }
        User userDetails = new User(userInfo.getName(),userInfo.getPassword(),authorities);
        return userDetails;
    }
}
