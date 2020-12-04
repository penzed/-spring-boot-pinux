package com.pinux.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pinux.entity.permission.Permission;
import com.pinux.entity.role.Role;
import com.pinux.entity.rolePermission.RolePermission;
import com.pinux.entity.user.User;
import com.pinux.entity.userRole.UserRole;
import com.pinux.service.permission.PermissionService;
import com.pinux.service.role.RoleService;
import com.pinux.service.rolePermission.RolePermissionService;
import com.pinux.service.user.UserService;
import com.pinux.service.userRole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author somebody
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        // TODO: 2020/12/4 添加菜单url和登录限制
        List<UserRole> userRoles = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id", user.getId()));
        List<Role> roles = userRoles.stream().map(userRole -> roleService.getById(userRole.getRoleId())).collect(Collectors.toList());
        user.setRoles(roles);
        List<RolePermission> rolePermissions = rolePermissionService.list(new QueryWrapper<RolePermission>().in("role_id", roles.stream().map(role -> role.getId()).collect(Collectors.toList())));
        List<Permission> permissions = rolePermissions.stream().map(rolePermission -> permissionService.getById(rolePermission.getPermissionId())).collect(Collectors.toList());
        user.setPermissions(permissions);
        return new SecurityUserDetails(user);
    }
}
