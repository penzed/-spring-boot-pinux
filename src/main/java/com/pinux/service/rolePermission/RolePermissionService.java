package com.pinux.service.rolePermission;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pinux.entity.permission.Permission;
import com.pinux.entity.rolePermission.RolePermission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinux
 * @since 2019-11-14
 */
public interface RolePermissionService extends IService<RolePermission> {

    List<Permission> findByUserName(String userName);

}
