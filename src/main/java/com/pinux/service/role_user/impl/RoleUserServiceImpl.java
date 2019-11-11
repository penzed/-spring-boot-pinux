package com.pinux.service.role_user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pinux.entity.role.Role;
import com.pinux.entity.role_user.RoleUser;
import com.pinux.entity.user.UserInfo;
import com.pinux.mapper.role_user.RoleUserMapper;
import com.pinux.service.role.RoleService;
import com.pinux.service.role_user.RoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pinux.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pinux
 * @since 2019-11-10
 */
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements RoleUserService {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RoleService roleService;

    @Override
    public List<Role> getRolesByUserName(String userName) {
        UserInfo userInfo=userInfoService.findByName(userName);
        if (userInfo!=null){
            List<RoleUser> roleUsers=this.list(new QueryWrapper<RoleUser>().eq("user_id",userInfo.getId()));
            List<String> roleIds=new ArrayList<>();
            if (roleUsers!=null){
                for (RoleUser roleUser:roleUsers){
                    roleIds.add(roleUser.getRoleId());
                }
            }
            List<Role> roles=roleService.list(new QueryWrapper<Role>().in("id",roleIds));
            return roles;
        }

        return null;
    }
}
