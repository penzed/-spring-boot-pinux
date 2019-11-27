package com.pinux.service.rolePermission.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pinux.entity.permission.Permission;
import com.pinux.entity.rolePermission.RolePermission;
import com.pinux.mapper.rolePermission.RolePermissionMapper;
import com.pinux.service.rolePermission.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pinux
 * @since 2019-11-14
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Permission> findByUserName(String userName) {
        return rolePermissionMapper.findByUserName(userName);
    }
}
