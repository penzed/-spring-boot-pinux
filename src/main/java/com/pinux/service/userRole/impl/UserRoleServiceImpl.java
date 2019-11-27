package com.pinux.service.userRole.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pinux.entity.role.Role;
import com.pinux.entity.user.User;
import com.pinux.entity.userRole.UserRole;
import com.pinux.mapper.userRole.UserRoleMapper;
import com.pinux.service.role.RoleService;
import com.pinux.service.user.UserService;
import com.pinux.service.userRole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pinux
 * @since 2019-11-14
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public List<Role> findByUserName(String userName) {
        User user=userService.getOne(new QueryWrapper<User>().eq("user_name",userName));
        List<UserRole> userRoles=this.list(new QueryWrapper<UserRole>().eq("user_id",user.getId()));
        List<String> ids=userRoles.stream().map(x->x.getRoleId()).collect(Collectors.toList());
        List<Role> roles=roleService.list(new QueryWrapper<Role>().in("id",ids));
        return roles;
    }
}
