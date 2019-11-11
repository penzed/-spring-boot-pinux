package com.pinux.service.user.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pinux.entity.role.Role;
import com.pinux.entity.role_user.RoleUser;
import com.pinux.entity.user.UserInfo;
import com.pinux.mapper.user.UserInfoMapper;
import com.pinux.service.role.RoleService;
import com.pinux.service.role_user.RoleUserService;
import com.pinux.service.user.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author pinux
 * @since 2019-11-10
 */
@Service
@Transactional
@CacheConfig(cacheNames = "UserServiceImpl")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private RoleUserService roleUserService;

    @Autowired
    RoleService roleUser;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Cacheable
    public UserInfo findByName(String aaa) {
        return this.getOne(new QueryWrapper<UserInfo>().eq("name", aaa));
    }


    @Override
    public void saveUserInfo(UserInfo user,List<Role> roles) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.save(user);
        if (roles!=null&&roles.size()>0){
            for (Role role:roles){
                Role r=roleUser.getOne(new QueryWrapper<Role>().eq("name",role.getName()));
                RoleUser roleUser=new RoleUser();
                roleUser.setId(UUID.fastUUID().toString());
                roleUser.setRoleId(r.getId());
                roleUser.setUserId(user.getId().toString());
                roleUserService.save(roleUser);
            }
        }

    }
}
