package com.pinux.service.role_user;

import com.pinux.entity.role.Role;
import com.pinux.entity.role_user.RoleUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pinux
 * @since 2019-11-10
 */
public interface RoleUserService extends IService<RoleUser> {

    List<Role> getRolesByUserName(String userName);

}
